package ru.starfactory.core.serial.usb.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.starfactory.core.serial.usb.domian.UsbSerialDevice

internal interface UsbSerialServiceJvm : UsbSerialService

internal class UsbSerialServiceJvmImpl : UsbSerialServiceJvm {
    override fun observeUsbSerialDevices(): Flow<List<UsbSerialDevice>> = emptyFlow()
}
