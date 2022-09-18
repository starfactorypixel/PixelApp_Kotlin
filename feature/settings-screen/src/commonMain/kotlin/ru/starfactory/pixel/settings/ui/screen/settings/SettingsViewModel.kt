package ru.starfactory.pixel.settings.ui.screen.settings

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.ecu_connection.ui.screen.select_source.SelectSourceScreen
import ru.starfactory.pixel.keep_screen_on.domain.KeepScreenOnInteractor
import ru.starfactory.pixel.settings.ui.screen.settings.SettingsViewState.MenuItem
import ru.starfactory.pixel.settings.ui.screen.settings.SettingsViewState.MenuItemId
import ru.starfactory.pixel.settings.ui.screen.settings.SettingsViewState.MenuItemState

internal class SettingsViewModel(
    private val keepScreenOnInteractor: KeepScreenOnInteractor,
    private val rootNavigation: StackNavigation<Screen>,
) : ViewModel() {

    val state = keepScreenOnInteractor.observeIsScreenAlwaysOn().map { getState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsViewState.Loading)

    fun onCLickClose() {
        rootNavigation.pop()
    }

    private fun getState(isAlwaysOn: Boolean): SettingsViewState.ShowSettings {
        val menuItems = listOf(
            MenuItem(MenuItemId.DATA_SOURCE, MenuItemState.None),
            MenuItem(MenuItemId.ALWAYS_ON_DISPLAY, MenuItemState.SwitcherState(isAlwaysOn)),
            MenuItem(MenuItemId.THEME, MenuItemState.None),
            MenuItem(MenuItemId.FAST_ACTION, MenuItemState.None),
            MenuItem(MenuItemId.LICENSE, MenuItemState.None),
            MenuItem(MenuItemId.ABOUT, MenuItemState.None),
        )
        return SettingsViewState.ShowSettings(menuItems)
    }

    fun onClickMenuItem(menuItem: MenuItem) {
        when (menuItem.id) {
            MenuItemId.DATA_SOURCE -> rootNavigation.push(SelectSourceScreen)
            MenuItemId.THEME -> Unit
            MenuItemId.FAST_ACTION -> Unit
            MenuItemId.LICENSE -> Unit
            MenuItemId.ABOUT -> Unit
            MenuItemId.ALWAYS_ON_DISPLAY -> {
                viewModelScope.launch {
                    val newState = (menuItem.state as MenuItemState.SwitcherState).isEnabled.not()
                    keepScreenOnInteractor.setIsScreenAlwaysOn(newState)
                }
            }
        }
    }
}
