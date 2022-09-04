package ru.starfactory.core.serial.usb.service

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.serial.usb.domian.UsbSerialDeviceInfo

interface UsbSerialService {
    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDeviceInfo>>
}