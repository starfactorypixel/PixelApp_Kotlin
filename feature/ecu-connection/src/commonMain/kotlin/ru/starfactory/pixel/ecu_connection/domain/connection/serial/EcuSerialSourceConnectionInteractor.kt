package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
) : EcuSerialSourceConnectionInteractor {
    override fun observePrimaryState(): Flow<EcuPrimaryState> {

        return flow {
            serialInteractor.connect(serialDevice) {
                val ecuProtocol = EcuProtocol(EcuProtocolRaw(it.inputStream, it.outputStream))
                while (true) {
                    val speed = ecuProtocol.readUByteRegister(125)
                    val voltage = ecuProtocol.readUIntRegister(174)

                    emit(
                        EcuPrimaryState(
                            speed = speed.toInt(),
                            batteryCharge = voltage.toFloat() / 1000, // TODO this not a charge I know
                        )
                    )

                    delay(1000)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
