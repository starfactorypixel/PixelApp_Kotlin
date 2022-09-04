package ru.starfactory.core.serial.bluetooth

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialDevicesProvider
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialDevicesProviderImpl

fun Modules.coreSerialBluetooth() = DI.Module("core-serial-bluetooth") {
    bindSingleton<BluetoothSerialDevicesProvider> { BluetoothSerialDevicesProviderImpl(i()) }
}
