package ru.starfactory.pixel

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.starfactory.pixel.domain.theme.ThemeInteractor
import ru.starfactory.pixel.domain.theme.ThemeInteractorImpl
import ru.starfactory.pixel.ui.screen.settings.SettingsViewModel

@Suppress("FunctionName")
fun MainModule() = DI.Module("main-module") {
    // Domain
    bindSingleton<ThemeInteractor> { ThemeInteractorImpl() }

    // View Models
    bindProvider { SettingsViewModel(instance()) }
}