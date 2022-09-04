package ru.starfactory.core.serial.bluetooth.domain

import ru.starfactory.core.serial.domain.SerialDeviceInfo
import ru.starfactory.core.serial.domain.SerialDeviceType

data class BluetoothSerialDeviceInfo(override val type: SerialDeviceType, override val name: String) : SerialDeviceInfo