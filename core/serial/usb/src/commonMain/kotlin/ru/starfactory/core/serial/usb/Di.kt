package ru.starfactory.core.serial.usb

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.serial.usb.domian.UsbSerialInteractor
import ru.starfactory.core.serial.usb.domian.UsbSerialInteractorImpl

fun Modules.coreSerialUsb() = DI.Module("core-serial-usb") {
    bindSingleton<UsbSerialInteractor> { UsbSerialInteractorImpl() }
}