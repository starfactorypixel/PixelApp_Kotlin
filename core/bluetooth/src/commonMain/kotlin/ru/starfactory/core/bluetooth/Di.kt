package ru.starfactory.core.bluetooth

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.bluetooth.domain.BluetoothInteractor
import ru.starfactory.core.bluetooth.domain.BluetoothInteractorImpl
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i

fun Modules.coreBluetooth() = DI.Module("core-bluetooth") {
    importOnce(Modules.coreBluetoothPlatform())
    bindSingleton<BluetoothInteractor> { BluetoothInteractorImpl(i(), i()) }
}

internal expect fun Modules.coreBluetoothPlatform(): DI.Module
