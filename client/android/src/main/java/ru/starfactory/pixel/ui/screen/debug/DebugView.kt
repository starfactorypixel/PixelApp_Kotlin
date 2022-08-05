package ru.starfactory.pixel.ui.screen.debug

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.pixel.R
import ru.starfactory.pixel.ui.screen.UsbScreen

@Composable
fun DebugView() {
    val navigation = LocalNavigation.current
    DebugContent(
        onClickUsbDevices = { navigation.push(UsbScreen) }
    )
}

@Composable
fun DebugContent(
    onClickUsbDevices: () -> Unit = {}
) {
    Column {
        Divider()
        MenuButton(text = "USB Devices", icon = painterResource(id = R.drawable.ic_usb), onClick = onClickUsbDevices)
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