package ru.starfactory.pixel.ui.screen.debug

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.receiveAsFlow
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.pixel.R

@Composable
fun DebugView(viewModel: DebugViewModel = decomposeViewModel()) {
    val navigation = LocalNavigation.current
    LaunchedEffect(viewModel, navigation) {
        viewModel.navigateTo.receiveAsFlow().collect { navigation.push(it) }
    }
    DebugContent(
        onClickUsbDevices = viewModel::onClickUsbDevices,
        onClickUsbSerialTerminal = viewModel::onClickUsbSerialTerminal,
        onBluetoothSerialTerminal = viewModel::onClickBluetoothSerialTerminal,
    )
}

@Composable
fun DebugContent(
    onClickUsbDevices: () -> Unit = {},
    onClickUsbSerialTerminal: () -> Unit = {},
    onBluetoothSerialTerminal: () -> Unit = {},
) {
    Column {
        Divider()
        MenuButton(text = "USB Devices", icon = painterResource(id = R.drawable.ic_usb), onClick = onClickUsbDevices)
        MenuButton(text = "USB-Serial Terminal", icon = painterResource(id = R.drawable.ic_usb), onClick = onClickUsbSerialTerminal)
        MenuButton(
            text = "Bluetooth-Serial Terminal",
            icon = painterResource(id = R.drawable.ic_bluetooth),
            onClick = onBluetoothSerialTerminal
        )
    }
}


@Composable
private fun MenuButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp, 8.dp)
            .defaultMinSize(minHeight = 32.dp)
    ) {

        Icon(
            painter = icon,
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
    Divider()
}