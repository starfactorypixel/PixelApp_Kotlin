package ru.starfactory.pixel.ui.screen.settings

data class SettingsViewState(val theme: Theme) {
    enum class Theme {
        LIGHT,
        DARK,
        SYSTEM,
    }
}