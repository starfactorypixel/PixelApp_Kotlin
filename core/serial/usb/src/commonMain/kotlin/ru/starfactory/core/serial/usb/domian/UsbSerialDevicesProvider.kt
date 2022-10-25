package ru.starfactory.core.serial.usb.domian

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.serial.domain.SerialConnection
import ru.starfactory.core.serial.domain.SerialDevicesProvider
import ru.starfactory.core.serial.usb.service.UsbSerialService

interface UsbSerialDevicesProvider : SerialDevicesProvider {
    override fun observeSerialDevicesInfo(): Flow<Map<String, UsbSerialDeviceInfo>>
}

internal class UsbSerialDevicesProviderImpl(
    private val usbSerialService: UsbSerialService,
) : UsbSerialDevicesProvider {
    override fun observeSerialDevicesInfo(): Flow<Map<String, UsbSerialDeviceInfo>> = usbSerialService.observeUsbSerialDevices()
    override suspend fun connect(id: String, block: suspend (SerialConnection) -> Unit) {
        TODO("Not yet implemented")
    }
}
