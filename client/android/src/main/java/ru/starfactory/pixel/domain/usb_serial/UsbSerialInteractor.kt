package ru.starfactory.pixel.domain.usb_serial

import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.service.usb_serial.UsbSerialDevice
import ru.starfactory.pixel.service.usb_serial.UsbSerialService

interface UsbSerialInteractor {
    fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>>
}

class UsbSerialInteractorImpl(
    private val usbSerialService: UsbSerialService
) : UsbSerialInteractor {
    override fun observeUsbSerialDevices(): Flow<Map<String, UsbSerialDevice>> = usbSerialService.observeUsbSerialDevices()
}
