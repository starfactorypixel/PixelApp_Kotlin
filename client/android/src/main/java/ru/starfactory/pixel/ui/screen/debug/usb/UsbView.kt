package ru.starfactory.pixel.ui.screen.debug.usb

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.startfactory.core.decompose.view_model.decomposeViewModel

@Composable
fun UsbView(viewModel: UsbViewModel = decomposeViewModel()) {
    val state by viewModel.state.collectAsState()
    UsbContent(state)
}

@Composable
private fun UsbContent(state: UsbViewState) {
    when (state) {
        is UsbViewState.HasDevices -> UsbHasDevices(devices = state.devices)
        UsbViewState.Loading -> Unit // Loading is very fast, progress bar doesn't need
        UsbViewState.NoDevices -> UsbNoDevices()
    }
}

@Composable
private fun UsbNoDevices() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "No Devices", Modifier.align(Alignment.Center))
    }
}

@Composable
fun UsbHasDevices(devices: List<UsbViewState.UsbDeviceState>) {
    Divider()
    LazyColumn {
        items(devices, key = { it.name }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = it.name, style = MaterialTheme.typography.h6)
                Row {
                    Text(text = "Vendor", Modifier.weight(1f))
                    Text(text = "ID:0x${it.vendorId.toString(16)}", Modifier.weight(1f))
                    Text(text = "Name:${it.vendorName}", Modifier.weight(2f))
                }
                Row {
                    Text(text = "Product", Modifier.weight(1f))
                    Text(text = "ID:${it.productId.toString(16)}", Modifier.weight(1f))
                    Text(text = "Name:${it.productName}", Modifier.weight(2f))
                }
            }
            Divider()
        }
    }
}