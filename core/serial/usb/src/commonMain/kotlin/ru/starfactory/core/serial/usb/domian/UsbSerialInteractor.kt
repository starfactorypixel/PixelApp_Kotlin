package ru.starfactory.core.serial.usb.domian

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SourceTypeSerialInteractor
import ru.starfactory.core.serial.usb.service.UsbSerialService

interface UsbSerialInteractor : SourceTypeSerialInteractor {
    override fun observeSerialDevices(): Flow<List<UsbSerialDevice>>
}

internal class UsbSerialInteractorImpl(
    private val usbSerialService: UsbSerialService,
) : UsbSerialInteractor {
    override val sourceType: SerialDeviceType = SerialDeviceType.USB
    override fun observeSerialDevices(): Flow<List<UsbSerialDevice>> = usbSerialService.observeUsbSerialDevices()
}
