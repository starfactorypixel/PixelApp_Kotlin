package ru.starfactory.pixel.ui.screen.debug.usb_serial.select_params

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.push
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.pixel.ui.screen.UsbSerialTerminalScreen

@Composable
fun SelectSerialParamsView(usbDeviceName: String) {
    val navigation = LocalNavigation.current
    SelectSerialParamsContent(
        onClickConnect = { navigation.push(UsbSerialTerminalScreen(usbDeviceName)) }
    )
}

@Composable
fun SelectSerialParamsContent(
    onClickConnect: () -> Unit = {}
) {
    Column {
        Text(text = "Тут будет возможность настроить параметры serial соединения, такие как скорость итд")
        Button(onClick = onClickConnect) {
            Text(text = "Connect")
        }
    }
}
