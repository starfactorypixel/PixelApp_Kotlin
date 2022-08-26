package ru.starfactory.pixel.settings

import org.kodein.di.DI
import org.kodein.di.bindProvider
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.pixel.settings.ui.screen.settings.SettingsViewModel

fun Modules.featureSettingsScreen() = DI.Module("feature-settings-screen") {
    bindProvider { SettingsViewModel(i(tag = NavigationType.ROOT)) }
}