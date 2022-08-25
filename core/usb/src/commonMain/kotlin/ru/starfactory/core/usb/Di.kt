package ru.starfactory.core.usb

import org.kodein.di.DI
import ru.starfactory.core.di.Modules

fun Modules.coreUsb() = DI.Module("core-usb") {
    importOnce(Modules.coreUsbPlatform())
}

internal expect fun Modules.coreUsbPlatform(): DI.Module