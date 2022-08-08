package ru.starfactory.pixel.ui.screen.debug

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.domain.permission.PermissionInteractor
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
}