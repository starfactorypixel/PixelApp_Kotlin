package ru.starfactory.pixel.ui.screen.debug.usb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.starfactory.core.decompose.view_model.decomposeViewModel

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

}