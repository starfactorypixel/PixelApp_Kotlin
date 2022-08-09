package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import android.util.Log
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractor

class BluetoothSerialViewModel(
    private val bluetoothInteractor: BluetoothInteractor,
) : ViewModel() {

    init {
        val boundedDevices = bluetoothInteractor.getBoundedDevices()
        Log.i("AAAAA", boundedDevices.toString())
    }
}