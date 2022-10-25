package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.logger.Log
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_protocol.EcuMessage
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
                    coroutineScope {
                        Log.d(TAG) { "Connected to $serialDevice" }

                        val ecuProtocolRaw = EcuProtocolRaw(it.inputStream, it.outputStream)

                        fun sender(msg: EcuMessage) {
                            Log.t(TAG) { "Msg to $serialDevice: $msg" }
                            ecuProtocolRaw.writeMessage(msg)
                        }

                        val ecuProtocol = EcuProtocol(::sender)

                        val errorChannel = Channel<Exception>()
                        val lock = Mutex()
                        suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit) {
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

                        val receiveChannel = flow {
                            while (true) {
                                val msg = ecuProtocolRaw.readMessage()
                                Log.t(TAG) { "Msg from $serialDevice: $msg" }

                                if (msg.type == EcuMessage.Type.HANDSHAKE && msg.id == 0xFFFF) {
                                    withEcuProtocol {
                                        it.writeHandshake()
                                    }
                                }

                                emit(msg)
                            }
                        }.shareIn(this, SharingStarted.Eagerly, 0)

                        ecuProtocol.writeHandshake()

                        emit(
                            object : Connection.Connected {
                                override fun observeMessages(): Flow<EcuMessage> = receiveChannel
                                override suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit) = withEcuProtocol(block)
                            }
                        )

                        throw errorChannel.receive()
                    }
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
        return connectionObservable.flatMapLatest { emptyFlow() }
//        return connectionObservable
//            .flatMapLatest { connection ->
//                if (connection is Connection.Connected) {
//                    flow {
//                        while (true) {
//                            val ecuPrimaryState = connection.withEcuProtocol { ecuProtocol ->
//                                val speed = ecuProtocol.readUByteRegister(125)
//                                val voltage = ecuProtocol.readUIntRegister(174)
//
//                                EcuPrimaryState(
//                                    speed = speed.toInt(),
//                                    batteryCharge = voltage.toFloat() / 1000, // TODO this not a charge I know
//                                )
//                            }
//
//                            emit(ecuPrimaryState)
//
//                            delay(1000)
//                        }
//                    }
//                } else {
//                    emptyFlow()
//                }
//            }
//            .retry { true }
//            .onStart { emit(EcuPrimaryState(0, 0f)) }
//            .flowOn(Dispatchers.IO)
    }

    private sealed interface Connection {
        object Connecting : Connection
        interface Connected : Connection {
            fun observeMessages(): Flow<EcuMessage>
            suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit)
        }

        data class Error(val reason: Exception) : Connection
        object Reconnecting : Connection
    }

    companion object {
        private val TAG = EcuSerialSourceConnectionInteractor::class.simpleName!!
    }
}
