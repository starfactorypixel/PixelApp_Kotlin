package ru.starfactory.pixel.ui.screen

import androidx.compose.runtime.Composable
import kotlinx.parcelize.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.ui.screen.debug.DebugView
import ru.starfactory.pixel.ui.screen.debug.usb.UsbView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.UsbSerialView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.select_params.SelectSerialParamsView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.terminal.UsbSerialTerminalView
import ru.starfactory.pixel.ui.screen.main.MainView
import ru.starfactory.pixel.ui.screen.settings.SettingsView

@Parcelize
object MainScreen : Screen {
    @Composable
    override fun ScreenView() {
        MainView()
    }
}

@Parcelize
object DebugScreen : Screen {
    @Composable
    override fun ScreenView() {
        DebugView()
    }
}

@Parcelize
object UsbScreen : Screen {
    @Composable
    override fun ScreenView() {
        UsbView()
    }
}

@Parcelize
object UsbSerialScreen : Screen {
    @Composable
    override fun ScreenView() {
        UsbSerialView()
    }
}

@Parcelize
data class SelectUsbSerialParamsScreen(val usbDeviceName: String) : Screen {
    @Composable
    override fun ScreenView() {
        SelectSerialParamsView(usbDeviceName)
    }
}

@Parcelize
data class UsbSerialTerminalScreen(val usbDeviceName: String) : Screen {
    @Composable
    override fun ScreenView() {
        UsbSerialTerminalView(usbDeviceName)
    }
}

@Parcelize
object SettingsScreen : Screen {
    @Composable
    override fun ScreenView() {
        SettingsView()
    }
}
