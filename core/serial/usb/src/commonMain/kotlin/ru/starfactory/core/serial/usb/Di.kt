package ru.starfactory.core.serial.usb

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.usb.domian.UsbSerialDevicesProvider
import ru.starfactory.core.serial.usb.domian.UsbSerialDevicesProviderImpl

fun Modules.coreSerialUsb() = DI.Module("core-serial-usb") {
    importOnce(Modules.coreSerialUsbPlatform())
    bindSingleton<UsbSerialDevicesProvider> { UsbSerialDevicesProviderImpl(i()) }
}

internal expect fun Modules.coreSerialUsbPlatform(): DI.Module
