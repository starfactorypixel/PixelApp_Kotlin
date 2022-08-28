package ru.starfactory.core.serial.usb.service

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.serial.domain.SerialDevice

interface UsbSerialService {
    fun observeUsbSerialDevices(): Flow<List<SerialDevice>>
}