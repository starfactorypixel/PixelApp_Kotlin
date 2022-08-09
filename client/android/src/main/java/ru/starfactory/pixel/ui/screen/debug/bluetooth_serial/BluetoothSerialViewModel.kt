package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import kotlinx.coroutines.flow.*
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractor

class BluetoothSerialViewModel(
    bluetoothInteractor: BluetoothInteractor,
) : ViewModel() {

    val state: StateFlow<BluetoothSerialViewState> = bluetoothInteractor.observeBoundedDevices()
        .map { BluetoothSerialViewState.Data(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BluetoothSerialViewState.Loading)
}