package ru.starfactory.core.serial.usb

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.usb.service.UsbSerialService
import ru.starfactory.core.serial.usb.service.UsbSerialServiceAndroid
import ru.starfactory.core.serial.usb.service.UsbSerialServiceAndroidImpl

internal actual fun Modules.coreSerialUsbPlatform() = DI.Module("core-serial-usb-platform") {
    bindSingleton<UsbSerialServiceAndroid> { UsbSerialServiceAndroidImpl(i(), i()) }
    bindProvider<UsbSerialService> { i<UsbSerialServiceAndroid>() }
}
