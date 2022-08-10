package ru.starfactory.pixel.ui.screen.debug.bluetooth_serial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice

@Composable
fun BluetoothSerialView(viewModel: BluetoothSerialViewModel = decomposeViewModel()) {
    val state by viewModel.state.collectAsState()
    val navigation = LocalNavigation.current

    LaunchedEffect(viewModel, navigation) {
        viewModel.openScreen.receiveAsFlow().collect { navigation.push(it) }
    }

    BluetoothSerialContent(
        state,
        onClickBluetoothDevice = viewModel::onClickBluetoothDevice
    )
}

@Composable
private fun BluetoothSerialContent(
    state: BluetoothSerialViewState,
    onClickBluetoothDevice: (BluetoothDevice) -> Unit = {},
) {
    when (state) {
        is BluetoothSerialViewState.Data -> BluetoothSerialContentData(state, onClickBluetoothDevice)
        BluetoothSerialViewState.Loading -> Unit
    }
}

@Composable
private fun BluetoothSerialContentData(
    state: BluetoothSerialViewState.Data,
    onClickBluetoothDevice: (BluetoothDevice) -> Unit,
) {
    LazyColumn {
        item { Text(text = "Bounded devices:") }
        items(state.boundedDevices, key = { it.address }) {
            BluetoothDeviceContent(device = it, onClickBluetoothDevice)
            Divider()
        }
    }
}

@Composable
private fun BluetoothDeviceContent(
    device: BluetoothDevice,
    onClickBluetoothDevice: (BluetoothDevice) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onClickBluetoothDevice(device) }
    ) {
        Text(text = device.name)
        Text(text = device.address)
        Text(text = device.connectionState.toString())
    }
}