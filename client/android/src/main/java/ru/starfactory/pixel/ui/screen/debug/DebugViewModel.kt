package ru.starfactory.pixel.ui.screen.debug

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.domain.permission.PermissionInteractor
import ru.starfactory.pixel.service.permission.Permission
import ru.starfactory.pixel.ui.screen.BluetoothSerialScreen
import ru.starfactory.pixel.ui.screen.RequestPermissionScreen
import ru.starfactory.pixel.ui.screen.UsbScreen
import ru.starfactory.pixel.ui.screen.UsbSerialScreen

class DebugViewModel(private val permissionInteractor: PermissionInteractor) : ViewModel() {
    val navigateTo = Channel<Screen>()

    fun onClickUsbDevices() {
        viewModelScope.launch { navigateTo.send(UsbScreen) }
    }

    fun onClickUsbSerialTerminal() {
        viewModelScope.launch { navigateTo.send(UsbSerialScreen) }
    }

    fun onClickBluetoothSerialTerminal() {
        viewModelScope.launch {
            val screen = if (permissionInteractor.getIsPermissionGranted(Permission.BLUETOOTH_CONNECT)) {
                BluetoothSerialScreen
            } else {
                RequestPermissionScreen(Permission.BLUETOOTH_CONNECT, BluetoothSerialScreen)
            }
            navigateTo.send(screen)
        }
    }
}