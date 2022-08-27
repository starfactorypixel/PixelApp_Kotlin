package ru.starfactory.pixel.settings.ui.screen.settings

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import kotlinx.coroutines.flow.MutableStateFlow
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen

internal class SettingsViewModel(
    private val rootNavigation: StackNavigation<Screen>,
) : ViewModel() {

    val state = MutableStateFlow<SettingsViewState>(SettingsViewState.ShowSettings(SettingsViewState.MenuItem.values().toList()))

    fun onCLickClose() {
        rootNavigation.pop()
    }

    fun onClickMenuItem(menuItem: SettingsViewState.MenuItem) {
        when (menuItem) {
            SettingsViewState.MenuItem.DATA_SOURCE -> Unit
            SettingsViewState.MenuItem.THEME -> Unit
            SettingsViewState.MenuItem.FAST_ACTION -> Unit
            SettingsViewState.MenuItem.LICENSE -> Unit
            SettingsViewState.MenuItem.ABOUT -> Unit
        }
    }
}