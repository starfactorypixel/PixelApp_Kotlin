package ru.starfactory.pixel.ui.screen

import androidx.compose.runtime.Composable
import kotlinx.parcelize.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import ru.starfactory.pixel.service.permission.Permission
import ru.starfactory.pixel.ui.screen.debug.DebugView
import ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.BluetoothSerialView
import ru.starfactory.pixel.ui.screen.debug.bluetooth_serial.terminal.BluetoothSerialTerminalView
import ru.starfactory.pixel.ui.screen.debug.usb.UsbView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.UsbSerialView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.select_params.SelectSerialParamsView
import ru.starfactory.pixel.ui.screen.debug.usb_serial.terminal.UsbSerialTerminalView
import ru.starfactory.pixel.ui.screen.request_permission.RequestPermissionView
import ru.starfactory.pixel.ui.screen.settings.SettingsView

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
object BluetoothSerialScreen : Screen {
    @Composable
    override fun ScreenView() {
        BluetoothSerialView()
    }
}

@Parcelize
data class BluetoothSerialTerminalScreen(private val bluetoothDevice: BluetoothDevice) :
    Screen {
    @Composable
    override fun ScreenView() {
        BluetoothSerialTerminalView(bluetoothDevice)
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
data class RequestPermissionScreen(private val permission: Permission, private val nextScreen: Screen) :
    Screen {
    @Composable
    override fun ScreenView() {
        RequestPermissionView(permission = permission, nextScreen = nextScreen)
    }
}

@Parcelize
object SettingsScreen : Screen {
    @Composable
    override fun ScreenView() {
        SettingsView()
    }
}
