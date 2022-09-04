package ru.starfactory.core.serial.usb.domian

import ru.starfactory.core.serial.domain.SerialDeviceInfo
import ru.starfactory.core.serial.domain.SerialDeviceType

data class UsbSerialDeviceInfo(override val type: SerialDeviceType, override val name: String) : SerialDeviceInfo