package ru.starfactory.core.usb

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.usb.domain.UsbInteractor
import ru.starfactory.core.usb.domain.UsbInteractorImpl

fun Modules.coreUsb() = DI.Module("core-usb") {
    importOnce(Modules.coreUsbPlatform())
    bindSingleton<UsbInteractor> { UsbInteractorImpl(i()) }
}

internal expect fun Modules.coreUsbPlatform(): DI.Module
