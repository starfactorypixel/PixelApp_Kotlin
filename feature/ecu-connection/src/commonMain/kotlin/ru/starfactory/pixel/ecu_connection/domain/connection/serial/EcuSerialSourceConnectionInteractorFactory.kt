package ru.starfactory.pixel.ecu_connection.domain.connection.serial

import ru.starfactory.core.serial.domain.SerialDevice

internal interface EcuSerialSourceConnectionInteractorFactory {
    fun create(device: SerialDevice): EcuSerialSourceConnectionInteractor
}

internal class EcuSerialSourceConnectionInteractorFactoryImpl : EcuSerialSourceConnectionInteractorFactory {
    override fun create(device: SerialDevice): EcuSerialSourceConnectionInteractor {
        return EcuSerialSourceConnectionInteractorImpl()
    }
}