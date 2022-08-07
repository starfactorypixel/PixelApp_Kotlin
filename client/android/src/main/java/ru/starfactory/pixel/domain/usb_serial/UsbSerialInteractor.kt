package ru.starfactory.pixel.domain.usb_serial

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.service.usb_serial.UsbSerialDevice
import ru.starfactory.pixel.service.usb_serial.UsbSerialService

interface UsbSerialInteractor {
    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>>
    suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialService.UsbSerialConnection) -> Unit)
}

class UsbSerialInteractorImpl(
    private val usbSerialService: UsbSerialService
) : UsbSerialInteractor {
    override fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>> = usbSerialService.observeUsbSerialDevices()
    override suspend fun connect(deviceName: String, block: suspend CoroutineScope.(connection: UsbSerialService.UsbSerialConnection) -> Unit) =
        usbSerialService.connect(deviceName, block)
}
