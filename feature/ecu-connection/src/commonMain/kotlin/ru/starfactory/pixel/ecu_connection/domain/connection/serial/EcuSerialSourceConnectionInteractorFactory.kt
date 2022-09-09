package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import ru.starfactory.core.logger.Log
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialInteractor

internal interface EcuSerialSourceConnectionInteractorFactory {
    fun create(device: SerialDevice): EcuSerialSourceConnectionInteractor
}

internal class EcuSerialSourceConnectionInteractorFactoryImpl(
    private val serialInteractor: SerialInteractor
) : EcuSerialSourceConnectionInteractorFactory {
    override fun create(device: SerialDevice): EcuSerialSourceConnectionInteractor {
        Log.d(TAG) { "Creating instance for $device" }
        return EcuSerialSourceConnectionInteractorImpl(serialInteractor, device)
    }

    companion object {
        private val TAG = EcuSerialSourceConnectionInteractorFactory::class.simpleName!!
    }
}
