package ru.starfactory.pixel

import org.kodein.di.*
import ru.starfactory.core.apps.coreApps
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.key_value_storage.coreKeyValueStorage
import ru.starfactory.core.permission.corePermission
import ru.starfactory.core.serial.bluetooth.coreSerialBluetooth
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialInteractor
import ru.starfactory.core.serial.coreSerial
import ru.starfactory.core.serial.domain.SourceTypeSerialInteractor
import ru.starfactory.core.serial.usb.coreSerialUsb
import ru.starfactory.core.serial.usb.domian.UsbSerialInteractor
import ru.starfactory.core.usb.coreUsb
import ru.starfactory.feature.apps.featureApps
import ru.starfactory.pixel.dashboard_screen.featureDashboardScreen
import ru.starfactory.pixel.ecu_connection.featureEcuConnection
import ru.starfactory.pixel.main_screen.featureMainScreen
import ru.starfactory.pixel.settings.featureSettingsScreen
import ru.starfactory.pixel.theming.featureTheming
import ru.starfactory.pixel.ui.screen.root.RootViewModel

@Suppress("UnusedReceiverParameter")
fun Modules.mainCommonModule() = DI.Module("main-common-module") {
    // Core
    importOnce(Modules.coreApps())
    importOnce(Modules.coreKeyValueStorage())
    importOnce(Modules.corePermission())
    importOnce(Modules.coreSerial())
    importOnce(Modules.coreSerialBluetooth())
    importOnce(Modules.coreSerialUsb())
    importOnce(Modules.coreUsb())

    // Feature
    importOnce(Modules.featureApps())
    importOnce(Modules.featureEcuConnection())
    importOnce(Modules.featureDashboardScreen())
    importOnce(Modules.featureMainScreen())
    importOnce(Modules.featureSettingsScreen())
    importOnce(Modules.featureTheming())

    // Bindings
    inSet<SourceTypeSerialInteractor> { provider { i<UsbSerialInteractor>() } }
    inSet<SourceTypeSerialInteractor> { provider { i<BluetoothSerialInteractor>() } }

    // View Models
    bindProvider { RootViewModel() }
}