package ru.starfactory.pixel.main_screen

import org.kodein.di.DI
import org.kodein.di.bindProvider
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.pixel.main_screen.ui.screen.main.MainViewModel

fun Modules.featureMainScreen() = DI.Module("feature-main-screen") {
    bindProvider { MainViewModel(i(), i(tag = NavigationType.ROOT)) }
}
