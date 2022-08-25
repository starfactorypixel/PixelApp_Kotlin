package ru.starfactory.core.usb

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.usb.service.UsbService
import ru.starfactory.core.usb.service.UsbServiceAndroid
import ru.starfactory.core.usb.service.UsbServiceAndroidImpl

internal actual fun Modules.coreUsbPlatform(): DI.Module = DI.Module("core-ubs-platform") {
    bindSingleton<UsbServiceAndroid> { UsbServiceAndroidImpl(i(), i()) }
    bindProvider<UsbService> { i<UsbServiceAndroid>() }
}