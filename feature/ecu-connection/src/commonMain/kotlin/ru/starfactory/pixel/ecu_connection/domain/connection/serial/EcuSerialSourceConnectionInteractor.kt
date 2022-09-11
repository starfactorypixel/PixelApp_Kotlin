package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.logger.Log
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_protocol.EcuProtocol
import ru.starfactory.pixel.ecu_protocol.EcuProtocolRaw

internal interface EcuSerialSourceConnectionInteractor : EcuSourceConnectionInteractor

@Suppress("MagicNumber")
internal class EcuSerialSourceConnectionInteractorImpl(
    private val serialInteractor: SerialInteractor,
    private val serialDevice: SerialDevice,
    private val scope: CoroutineScope,
) : EcuSerialSourceConnectionInteractor {

    @Suppress("TooGenericExceptionCaught")
    private val connectionObservable = flow {
        emit(Connection.Connecting)
        Log.d(TAG) { "Start connection to $serialDevice" }

        while (true) {
            try {
                serialInteractor.connect(serialDevice) {
                    Log.d(TAG) { "Connected to $serialDevice" }

                    val ecuProtocol = EcuProtocol(EcuProtocolRaw(it.inputStream, it.outputStream))
                    val lock = Mutex()

                    val errorChannel = Channel<Exception>()

                    emit(
                        object : Connection.Connected {
                            override suspend fun <T> withEcuProtocol(block: suspend (EcuProtocol) -> T): T {
                                return lock.withLock {
                                    try {
                                        block(ecuProtocol)
                                    } catch (e: CancellationException) {
                                        throw e
                                    } catch (e: Exception) {
                                        errorChannel.send(e)
                                        throw e
                                    }
                                }
                            }
                        }
                    )

                    throw errorChannel.receive()
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.d(TAG) { "Connection error with $serialDevice" }
                emit(Connection.Error(e))
            }
            delay(2000)
            Log.d(TAG) { "Reconnecting to $serialDevice" }
            emit(Connection.Reconnecting)
        }
    }
        .shareDefault(scope)

    override fun observePrimaryState(): Flow<EcuPrimaryState> {
        return connectionObservable
            .flatMapLatest { connection ->
                if (connection is Connection.Connected) {
                    flow {
                        while (true) {
                            val ecuPrimaryState = connection.withEcuProtocol { ecuProtocol ->
                                val speed = ecuProtocol.readUByteRegister(125)
                                val voltage = ecuProtocol.readUIntRegister(174)

                                EcuPrimaryState(
                                    speed = speed.toInt(),
                                    batteryCharge = voltage.toFloat() / 1000, // TODO this not a charge I know
                                )
                            }

                            emit(ecuPrimaryState)

                            delay(1000)
                        }
                    }
                } else {
                    emptyFlow()
                }
            }
            .retry { true }
            .onStart { emit(EcuPrimaryState(0, 0f)) }
            .flowOn(Dispatchers.IO)
    }

    private sealed interface Connection {
        object Connecting : Connection
        interface Connected : Connection {
            suspend fun <T> withEcuProtocol(block: suspend (EcuProtocol) -> T): T
        }

        data class Error(val reason: Exception) : Connection
        object Reconnecting : Connection
    }

    companion object {
        private val TAG = EcuSerialSourceConnectionInteractor::class.simpleName!!
    }
}
