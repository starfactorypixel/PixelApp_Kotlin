package ru.starfactory.pixel.ui.screen.debug.usb_serial.terminal

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.startfactory.core.decompose.view_model.decomposeViewModelFactory

@Composable
fun UsbSerialTerminalView(usbDeviceName: String) {
    val viewModel: UsbSerialTerminalViewModel = decomposeViewModelFactory(usbDeviceName)
    val state by viewModel.state.collectAsState()
    UsbSerialTerminalContent(text = state)
}

@Composable
fun UsbSerialTerminalContent(text: String) {
    Text(text = text)
}