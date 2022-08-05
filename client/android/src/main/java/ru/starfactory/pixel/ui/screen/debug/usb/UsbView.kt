package ru.starfactory.pixel.ui.screen.debug.usb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

}

@Composable
fun UsbHasDevices(devices: List<UsbViewState.UsbDeviceState>) {

}