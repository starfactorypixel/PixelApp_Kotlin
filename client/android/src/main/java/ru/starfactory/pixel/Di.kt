package ru.starfactory.pixel

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.starfactory.pixel.domain.theme.ThemeInteractor
import ru.starfactory.pixel.domain.theme.ThemeInteractorImpl
import ru.starfactory.pixel.domain.usb.UsbInteractor
import ru.starfactory.pixel.domain.usb.UsbInteractorImpl
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractor
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractorImpl
import ru.starfactory.pixel.repository.theme.ThemeRepository
import ru.starfactory.pixel.repository.theme.ThemeRepositoryImpl
import ru.starfactory.pixel.service.usb.UsbService
import ru.starfactory.pixel.service.usb.UsbServiceImpl
import ru.starfactory.pixel.service.usb_serial.UsbSerialService
import ru.starfactory.pixel.service.usb_serial.UsbSerialServiceImpl
import ru.starfactory.pixel.ui.screen.debug.usb.UsbViewModel
import ru.starfactory.pixel.ui.screen.debug.usb_serial.UsbSerialViewModel
import ru.starfactory.pixel.ui.screen.settings.SettingsViewModel

@Suppress("FunctionName")
fun MainModule() = DI.Module("main-module") {
    // Service
    bindSingleton<UsbService> { UsbServiceImpl(instance(), instance()) }
    bindSingleton<UsbSerialService> { UsbSerialServiceImpl(instance(), instance()) }

    // Repository
    bindSingleton<ThemeRepository> { ThemeRepositoryImpl(instance()) }

    // Domain
    bindSingleton<ThemeInteractor> { ThemeInteractorImpl(instance()) }
    bindSingleton<UsbInteractor> { UsbInteractorImpl(instance()) }
    bindSingleton<UsbSerialInteractor> { UsbSerialInteractorImpl(instance()) }

    // View Models
    bindProvider { SettingsViewModel(instance()) }
    bindProvider { UsbViewModel(instance()) }
    bindProvider { UsbSerialViewModel(instance()) }
}