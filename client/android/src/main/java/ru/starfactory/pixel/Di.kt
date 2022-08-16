package ru.starfactory.pixel

import org.kodein.di.*
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractor
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractorImpl
import ru.starfactory.pixel.domain.permission.PermissionInteractor
import ru.starfactory.pixel.domain.permission.PermissionInteractorImpl
import ru.starfactory.pixel.domain.usb.UsbInteractor
import ru.starfactory.pixel.domain.usb.UsbInteractorImpl
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractor
import ru.starfactory.pixel.domain.usb_serial.UsbSerialInteractorImpl
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import ru.starfactory.pixel.service.bluetooth.BluetoothService
import ru.starfactory.pixel.service.bluetooth.BluetoothServiceImpl
import ru.starfactory.pixel.service.permission.Permission
import ru.starfactory.pixel.service.permission.PermissionService
import ru.starfactory.pixel.service.permission.PermissionServiceImpl
import ru.starfactory.pixel.service.usb.UsbService
import ru.starfactory.pixel.service.usb.UsbServiceImpl
import ru.starfactory.pixel.service.usb_serial.UsbSerialService
import ru.starfactory.pixel.service.usb_serial.UsbSerialServiceImpl
import ru.starfactory.pixel.ui.screen.debug.DebugViewModel
import ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.BluetoothSerialViewModel
import ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.terminal.BluetoothSerialTerminalViewModel
import ru.starfactory.pixel.ui.screen.debug.usb.UsbViewModel
import ru.starfactory.pixel.ui.screen.debug.usb_serial.UsbSerialViewModel
import ru.starfactory.pixel.ui.screen.debug.usb_serial.terminal.UsbSerialTerminalViewModel
import ru.starfactory.pixel.ui.screen.permission.PermissionViewModel
import ru.starfactory.pixel.ui.screen.request_permission.RequestPermissionViewModel
import ru.starfactory.pixel.ui.screen.settings.SettingsViewModel
import ru.starfactory.core.di.Modules
import ru.starfactory.core.key_value_storage.coreKeyValueStorage
import ru.starfactory.pixel.main_screen.featureMainScreen
import ru.starfactory.pixel.theming.featureTheming

@Suppress("UnusedReceiverParameter")
fun Modules.mainAndroidModule() = DI.Module("main-android-module") {
    // Core
    importOnce(Modules.coreKeyValueStorage())

    // Feature
    importOnce(Modules.featureMainScreen())
    importOnce(Modules.featureTheming())

    // Service
    bindSingleton<BluetoothService> { BluetoothServiceImpl(instance()) }
    bindSingleton<PermissionService> { PermissionServiceImpl(instance()) }
    bindSingleton<UsbService> { UsbServiceImpl(instance(), instance()) }
    bindSingleton<UsbSerialService> { UsbSerialServiceImpl(instance(), instance()) }

    // Repository

    // Domain
    bindSingleton<BluetoothInteractor> { BluetoothInteractorImpl(instance()) }
    bindSingleton<PermissionInteractor> { PermissionInteractorImpl(instance()) }
    bindSingleton<UsbInteractor> { UsbInteractorImpl(instance()) }
    bindSingleton<UsbSerialInteractor> { UsbSerialInteractorImpl(instance()) }

    // View Models
    bindProvider { SettingsViewModel(instance()) }
    bindProvider { UsbViewModel(instance()) }
    bindProvider { UsbSerialViewModel(instance(), instance()) }
    bindProvider { PermissionViewModel(instance()) }
    bindProvider { DebugViewModel(instance()) }
    bindProvider { BluetoothSerialViewModel(instance()) }
    bindFactory<Permission, RequestPermissionViewModel> { RequestPermissionViewModel(instance(), it) }
    bindFactory<String, UsbSerialTerminalViewModel> { UsbSerialTerminalViewModel(instance(), it) }
    bindFactory<BluetoothDevice, BluetoothSerialTerminalViewModel> { BluetoothSerialTerminalViewModel(instance(), it) }
}