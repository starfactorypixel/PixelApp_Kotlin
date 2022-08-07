package ru.starfactory.pixel.ui.screen.debug.usb_serial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.core.navigation.ui.LocalNavigationHolder
import ru.starfactory.pixel.ui.screen.SelectUsbSerialParamsScreen

@Composable
fun UsbSerialView(viewModel: UsbSerialViewModel = decomposeViewModel()) {
    val navigation = LocalNavigation.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel, navigation) {
        viewModel.navigateToSelectUsbParamsScreen.receiveAsFlow().collect { navigation.push(SelectUsbSerialParamsScreen(it)) }
    }

    UsbSerialContent(
        state,
        onClickDevice = viewModel::onClickDevice
    )
}

@Composable
private fun UsbSerialContent(
    state: UsbSerialViewState,
    onClickDevice: (deviceName: String) -> Unit = {}
) {
    when (state) {
        is UsbSerialViewState.HasDevices -> UsbSerialHasDevices(devices = state.devices, onClickDevice = onClickDevice)
        UsbSerialViewState.Loading -> Unit // Loading is very fast, progress bar doesn't need
        UsbSerialViewState.NoDevices -> UsbSerialNoDevices()
    }
}

@Composable
private fun UsbSerialNoDevices() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "No Devices", Modifier.align(Alignment.Center))
    }
}

@Composable
private fun UsbSerialHasDevices(
    devices: List<UsbSerialViewState.UsbSerialDeviceState>,
    onClickDevice: (deviceName: String) -> Unit = {}
) {
    Divider()
    LazyColumn {
        items(devices, key = { it.name }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable(enabled = it.driverState is UsbSerialViewState.UsbSerialDeviceDriverState.HasDriver) { onClickDevice(it.name) }
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
                DriverContent(driver = it.driverState)
            }
            Divider()
        }
    }
}

@Composable
fun DriverContent(driver: UsbSerialViewState.UsbSerialDeviceDriverState) {
    when (driver) {
        is UsbSerialViewState.UsbSerialDeviceDriverState.HasDriver -> Text(text = "Driver: ${driver.name}")
        UsbSerialViewState.UsbSerialDeviceDriverState.NoDriver -> Text(
            text = "Drivers for this devices not found!",
            color = MaterialTheme.colors.error
        )
    }
}