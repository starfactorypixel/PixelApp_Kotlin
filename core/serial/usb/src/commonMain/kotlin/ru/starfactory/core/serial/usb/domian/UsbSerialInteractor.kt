package ru.starfactory.core.serial.usb.domian

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.usb.service.UsbSerialService

interface UsbSerialInteractor {
    fun observeUsbSerialDevices(): Flow<List<SerialDevice>>
}

internal class UsbSerialInteractorImpl(
    private val usbSerialService: UsbSerialService,
) : UsbSerialInteractor {
    override fun observeUsbSerialDevices(): Flow<List<SerialDevice>> = usbSerialService.observeUsbSerialDevices()
}
