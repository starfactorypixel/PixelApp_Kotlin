package ru.starfactory.core.serial.usb.domain

import com.hoho.android.usbserial.driver.UsbSerialDriver
import ru.starfactory.core.usb.domain.UsbDevice
import android.hardware.usb.UsbDevice as UsbDeviceAndroid


data class UsbSerialDeviceAndroid(
    val device: UsbDevice,
    val deviceAndroid: UsbDeviceAndroid,
    val driver: UsbSerialDriver?,
)