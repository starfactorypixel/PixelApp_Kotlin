package ru.starfactory.pixel

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.starfactory.pixel.domain.theme.ThemeInteractor
import ru.starfactory.pixel.domain.theme.ThemeInteractorImpl
import ru.starfactory.pixel.repository.theme.ThemeRepository
import ru.starfactory.pixel.repository.theme.ThemeRepositoryImpl
import ru.starfactory.pixel.ui.screen.settings.SettingsViewModel

@Suppress("FunctionName")
fun MainModule() = DI.Module("main-module") {
    // Repository
    bindSingleton<ThemeRepository> { ThemeRepositoryImpl(instance()) }

    // Domain
    bindSingleton<ThemeInteractor> { ThemeInteractorImpl(instance()) }

    // View Models
    bindProvider { SettingsViewModel(instance()) }
}