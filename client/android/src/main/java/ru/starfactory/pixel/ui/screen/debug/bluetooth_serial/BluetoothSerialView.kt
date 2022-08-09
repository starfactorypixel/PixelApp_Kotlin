package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice

@Composable
fun BluetoothSerialView(viewModel: BluetoothSerialViewModel = decomposeViewModel()) {
    val state by viewModel.state.collectAsState()
    BluetoothSerialContent(state)
}

@Composable
private fun BluetoothSerialContent(state: BluetoothSerialViewState) {
    when (state) {
        is BluetoothSerialViewState.Data -> BluetoothSerialContentData(state)
        BluetoothSerialViewState.Loading -> Unit
    }
}

@Composable
private fun BluetoothSerialContentData(state: BluetoothSerialViewState.Data) {
    LazyColumn {
        item { Text(text = "Bounded devices:") }
        items(state.boundedDevices, key = { it.address }) {
            BluetoothDeviceContent(device = it)
            Divider()
        }
    }
}

@Composable
private fun BluetoothDeviceContent(device: BluetoothDevice) {
    Column {
        Text(text = device.name)
        Text(text = device.address)
        Text(text = device.connectionState.toString())
    }
}