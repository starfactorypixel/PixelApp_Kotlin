package ru.starfactory.pixel.service.usb_serial

import android.hardware.usb.UsbDevice
import com.hoho.android.usbserial.driver.UsbSerialDriver

data class UsbSerialDevice(
    val device: UsbDevice,
    val driver: UsbSerialDriver?,
)