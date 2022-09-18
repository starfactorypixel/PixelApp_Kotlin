package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.runtime.Immutable

sealed class SettingsViewState {
    object Loading : SettingsViewState()

    @Immutable
    data class ShowSettings(val menuItems: List<MenuItem>) : SettingsViewState()

    data class MenuItem(val id: MenuItemId, val state: MenuItemState)

    sealed class MenuItemState {
        object None : MenuItemState()
        data class SwitcherState(val isEnabled: Boolean) : MenuItemState()
    }

    enum class MenuItemId {
        DATA_SOURCE,
        THEME,
        FAST_ACTION,
        LICENSE,
        ABOUT,
        ALWAYS_ON_DISPLAY,
    }
}
