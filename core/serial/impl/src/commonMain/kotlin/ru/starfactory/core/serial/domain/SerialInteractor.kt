package ru.starfactory.core.serial.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.starfactory.core.serial.usb.domian.UsbSerialInteractor

interface SerialInteractor {
    fun observeSerialDevices(): Flow<List<SerialDevice>>
}

internal class SerialInteractorImpl(
    private val usbSerialInteractor: UsbSerialInteractor
) : SerialInteractor {
    override fun observeSerialDevices(): Flow<List<SerialDevice>> {
        return combine(usbSerialInteractor.observeUsbSerialDevices()) { sources ->
            sources.flatMap { it }
        }
    }
}