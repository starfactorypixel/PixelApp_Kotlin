package ru.starfactory.core.serial.usb

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.usb.service.*
import ru.starfactory.core.serial.usb.service.UsbSerialServiceJvm

internal actual fun Modules.coreSerialUsbPlatform() = DI.Module("core-serial-usb-platform") {
    bindSingleton<UsbSerialServiceJvm> { UsbSerialServiceJvmImpl() }
    bindProvider<UsbSerialService> { i<UsbSerialServiceJvm>() }
}