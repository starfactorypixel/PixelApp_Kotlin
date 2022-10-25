package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceLowLevelConnectionInteractor.Connection
import ru.starfactory.pixel.ecu_protocol.EcuMessage
import java.nio.ByteBuffer

internal interface EcuSerialSourceConnectionInteractor : EcuSourceConnectionInteractor

@Suppress("MagicNumber")
internal class EcuSerialSourceConnectionInteractorImpl(
    private val ll: EcuSerialSourceLowLevelConnectionInteractor,
    private val scope: CoroutineScope,
) : EcuSerialSourceConnectionInteractor {

    private val seed = createUByteSubscriptionFlow(125)
    private val voltage = createUIntSubscriptionFlow(174)

    override fun observePrimaryState(): Flow<EcuPrimaryState> {
        return combine(seed, voltage) { seed, voltage ->
            EcuPrimaryState(seed.toInt(), voltage.toFloat() / 1000)
        }
    }

    private fun createUByteSubscriptionFlow(id: Int): Flow<UByte> = createRawSubscriptionFlow(id) { it.get().toUByte() }
    private fun createUIntSubscriptionFlow(id: Int): Flow<UInt> = createRawSubscriptionFlow(id) { it.int.toUInt() }
    private fun <T> createRawSubscriptionFlow(id: Int, dataMapper: (ByteBuffer) -> T): Flow<T> {
        return ll.observeConnection().flatMapLatest { connection ->
            if (connection is Connection.Connected) {
                channelFlow {

                    val listenJob = launch {
                        connection.observeMessages().collect {
                            if ((it.type == EcuMessage.Type.SUBSCRIPTIONS || it.type == EcuMessage.Type.EVENT) && it.id == id) {
                                channel.send(it.data)
                            }
                        }
                    }

                    try {
                        connection.withEcuProtocol { ecuProtocol ->
                            ecuProtocol.subscribe(id)
                        }
                        listenJob.join()
                    } finally {
                        withContext(NonCancellable) {
                            connection.withEcuProtocol { ecuProtocol ->
                                ecuProtocol.unsubscribe(id)
                            }
                        }
                    }
                }
            } else {
                emptyFlow()
            }
        }
            .map { dataMapper(ByteBuffer.wrap(it)) }
            .retry { true }
            .shareDefault(scope + Dispatchers.IO)
    }
}
