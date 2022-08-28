package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.runtime.Immutable

sealed class SettingsViewState {
    object Loading : SettingsViewState()

    @Immutable
    data class ShowSettings(val menuItems: List<MenuItem>) : SettingsViewState()

    enum class MenuItem {
        DATA_SOURCE,
        THEME,
        FAST_ACTION,
        LICENSE,
        ABOUT
    }
}
