package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import ru.starfactory.pixel.service.bluetooth.BluetoothDevice

sealed class BluetoothSerialViewState {
    object Loading : BluetoothSerialViewState()
    data class Data(
        val boundedDevices: List<BluetoothDevice>
    ) : BluetoothSerialViewState()
}