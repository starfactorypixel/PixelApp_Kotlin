package ru.starfactory.pixel.theming

import org.kodein.di.*
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.pixel.theming.domain.ThemeInteractor
import ru.starfactory.pixel.theming.domain.ThemeInteractorImpl
import ru.starfactory.pixel.theming.repository.ThemeRepository
import ru.starfactory.pixel.theming.repository.ThemeRepositoryImpl
import ru.starfactory.pixel.theming.ui.theme.ThemeViewModel

fun Modules.featureTheming() = DI.Module("feature-theming") {
    bindSingleton<ThemeRepository> { ThemeRepositoryImpl(i()) }
    bindSingleton<ThemeInteractor> { ThemeInteractorImpl(i()) }
    bindProvider { ThemeViewModel(i()) }
}
