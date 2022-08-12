package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.terminal

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.startfactory.core.decompose.view_model.decomposeViewModelFactory
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice

@Composable
fun BluetoothSerialTerminalView(bluetoothDevice: BluetoothDevice) {
    val viewModel: BluetoothSerialTerminalViewModel = decomposeViewModelFactory(argument = bluetoothDevice)
    val state by viewModel.state.collectAsState()
    BluetoothSerialTerminalContent(state)
}

@Composable
private fun BluetoothSerialTerminalContent(state: String) {
    Text(state)
}