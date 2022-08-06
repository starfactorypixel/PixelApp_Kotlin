package ru.starfactory.pixel.domain.usb_serial

import ru.starfactory.pixel.service.usb_serial.UsbSerialService

interface UsbSerialInteractor {
}

class UsbSerialInteractorImpl(
    private val usbSerialService: UsbSerialService
) : UsbSerialInteractor {}
