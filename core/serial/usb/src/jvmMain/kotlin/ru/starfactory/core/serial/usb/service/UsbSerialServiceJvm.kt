package ru.starfactory.core.serial.usb.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import ru.starfactory.core.serial.usb.domian.UsbSerialDeviceInfo

internal interface UsbSerialServiceJvm : UsbSerialService

internal class UsbSerialServiceJvmImpl : UsbSerialServiceJvm {
    override fun observeUsbSerialDevices():  Flow<Map<String, UsbSerialDeviceInfo>> = flowOf(emptyMap())
}