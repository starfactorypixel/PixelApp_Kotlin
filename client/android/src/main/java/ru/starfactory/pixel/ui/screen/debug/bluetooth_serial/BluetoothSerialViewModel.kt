package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.domain.bluetooth.BluetoothInteractor
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import ru.starfactory.pixel.ui.screen.BluetoothSerialTerminalScreen

class BluetoothSerialViewModel(
    bluetoothInteractor: BluetoothInteractor,
) : ViewModel() {

    val state: StateFlow<BluetoothSerialViewState> = bluetoothInteractor.observeBoundedDevices()
        .map { BluetoothSerialViewState.Data(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BluetoothSerialViewState.Loading)

    val openScreen = Channel<Screen>()

    fun onClickBluetoothDevice(bluetoothDevice: BluetoothDevice) {
        viewModelScope.launch { openScreen.send(BluetoothSerialTerminalScreen(bluetoothDevice)) }
    }
}