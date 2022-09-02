package ru.starfactory.core.bluetooth

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.bluetooth.service.BluetoothService
import ru.starfactory.core.bluetooth.service.JvmBluetoothService
import ru.starfactory.core.di.Modules

internal actual fun Modules.coreBluetoothPlatform() = DI.Module("core-bluetooth-platform") {
    bindSingleton<BluetoothService> { JvmBluetoothService() }
}
