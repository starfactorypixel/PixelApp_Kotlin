package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.starfactory.core.coroutines.shareDefault
import ru.starfactory.core.logger.Log
import ru.starfactory.core.serial.domain.SerialConnection
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceLowLevelConnectionInteractor.Connection
import ru.starfactory.pixel.ecu_protocol.EcuMessage
import ru.starfactory.pixel.ecu_protocol.EcuProtocol
import ru.starfactory.pixel.ecu_protocol.EcuProtocolRaw

/**
 * Low level protocol interactor
 * Initialize connection, error retry, ping check
 */
internal interface EcuSerialSourceLowLevelConnectionInteractor {
    fun observeConnection(): Flow<Connection>

    sealed interface Connection {
        object Connecting : Connection
        interface Connected : Connection {
            fun observeMessages(): Flow<EcuMessage>
            suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit)
        }

        data class Error(val reason: Exception) : Connection
        object Reconnecting : Connection
    }
}

internal class EcuSerialSourceLowLevelConnectionInteractorImpl(
    private val serialInteractor: SerialInteractor,
    private val serialDevice: SerialDevice,
    private val scope: CoroutineScope,
) : EcuSerialSourceLowLevelConnectionInteractor {

    @Suppress("TooGenericExceptionCaught")
    private val connectionObservable = flow {
        emit(Connection.Connecting)
        Log.d(TAG) { "Start connection to $serialDevice" }

        while (true) {
            try {
                serialInteractor.connect(serialDevice) { connection ->
                    Log.d(TAG) { "Connected to $serialDevice" }
                    processConnection(this, connection)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.d(TAG) { "Connection error with $serialDevice" }
                emit(Connection.Error(e))
            }
            delay(RECONNECTING_TIMEOUT)
            Log.d(TAG) { "Reconnecting to $serialDevice" }
            emit(Connection.Reconnecting)
        }
    }
        .shareDefault(scope + Dispatchers.IO)

    @Suppress("TooGenericExceptionCaught")
    private suspend fun processConnection(
        collector: FlowCollector<Connection>,
        connection: SerialConnection
    ): Unit = coroutineScope {

        val ecuProtocolRaw = EcuProtocolRaw(connection.inputStream, connection.outputStream)

        fun sender(msg: EcuMessage) {
            Log.t(TAG) { "Msg to $serialDevice: $msg" }
            ecuProtocolRaw.writeMessage(msg)
        }

        val ecuProtocol = EcuProtocol(::sender)

        val errorChannel = Channel<Exception>(capacity = 1)
        val lock = Mutex()
        suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit) {
            return lock.withLock {
                try {
                    block(ecuProtocol)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    errorChannel.trySend(e)
                    throw e
                }
            }
        }

        val receiveChannel = flow {
            while (true) {
                val msg = ecuProtocolRaw.readMessage()
                Log.t(TAG) { "Msg from $serialDevice: $msg" }

                // Response ping
                // TODO нужно тут добавить проверку, если давно нет пингов, закрывать соединение
                if (msg.type == EcuMessage.Type.HANDSHAKE && msg.id == HANDSHAKE_PING_ID) {
                    withEcuProtocol { ecuProtocol ->
                        ecuProtocol.writeMessage(msg)
                    }
                }

                emit(msg)
            }
        }.shareIn(this, SharingStarted.Eagerly, 0)

        ecuProtocol.writeHandshake()

        collector.emit(
            object : Connection.Connected {
                override fun observeMessages(): Flow<EcuMessage> = receiveChannel
                override suspend fun withEcuProtocol(block: suspend (EcuProtocol) -> Unit) = withEcuProtocol(block)
            }
        )

        throw errorChannel.receive()
    }

    override fun observeConnection(): Flow<Connection> {
        return connectionObservable
    }

    companion object {
        private val TAG = EcuSerialSourceLowLevelConnectionInteractor::class.simpleName!!
        private const val RECONNECTING_TIMEOUT = 2000L
        private const val HANDSHAKE_PING_ID = 0xFFFF
    }
}
