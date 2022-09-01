package ru.starfactory.core.serial.bluetooth

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialInteractor
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialInteractorImpl

fun Modules.coreSerialBluetooth() = DI.Module("core-serial-bluetooth") {
    bindSingleton<BluetoothSerialInteractor> { BluetoothSerialInteractorImpl(i()) }
}