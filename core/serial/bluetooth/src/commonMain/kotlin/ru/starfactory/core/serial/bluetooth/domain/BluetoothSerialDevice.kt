package ru.starfactory.core.serial.bluetooth.domain

import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialDeviceId
import ru.starfactory.core.serial.domain.SerialDeviceType

data class BluetoothSerialDevice(
    override val id: SerialDeviceId,
    override val type: SerialDeviceType,
    override val name: String
) : SerialDevice
