package ru.starfactory.pixel

import org.kodein.di.*
import ru.starfactory.core.di.Modules
import ru.starfactory.core.key_value_storage.coreKeyValueStorage
import ru.starfactory.pixel.main_screen.featureMainScreen
import ru.starfactory.pixel.theming.featureTheming

@Suppress("UnusedReceiverParameter")
fun Modules.mainCommonModule() = DI.Module("main-common-module") {
    // Core
    importOnce(Modules.coreKeyValueStorage())

    // Feature
    importOnce(Modules.featureMainScreen())
    importOnce(Modules.featureTheming())

    // Service
//    bindSingleton<BluetoothService> { BluetoothServiceImpl(instance()) }
//    bindSingleton<PermissionService> { PermissionServiceImpl(instance()) }
//    bindSingleton<UsbService> { UsbServiceImpl(instance(), instance()) }
//    bindSingleton<UsbSerialService> { UsbSerialServiceImpl(instance(), instance()) }

    // Repository

    // Domain
//    bindSingleton<BluetoothInteractor> { BluetoothInteractorImpl(instance()) }
//    bindSingleton<PermissionInteractor> { PermissionInteractorImpl(instance()) }
//    bindSingleton<UsbInteractor> { UsbInteractorImpl(instance()) }
//    bindSingleton<UsbSerialInteractor> { UsbSerialInteractorImpl(instance()) }

    // View Models
//    bindProvider { SettingsViewModel(instance()) }
//    bindProvider { UsbViewModel(instance()) }
//    bindProvider { UsbSerialViewModel(instance(), instance()) }
//    bindProvider { PermissionViewModel(instance()) }
//    bindProvider { DebugViewModel(instance()) }
//    bindProvider { BluetoothSerialViewModel(instance()) }
//    bindFactory<Permission, RequestPermissionViewModel> { RequestPermissionViewModel(instance(), it) }
//    bindFactory<String, UsbSerialTerminalViewModel> { UsbSerialTerminalViewModel(instance(), it) }
//    bindFactory<BluetoothDevice, BluetoothSerialTerminalViewModel> { BluetoothSerialTerminalViewModel(instance(), it) }
}