package ru.starfactory.pixel.keep_screen_on

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.pixel.keep_screen_on.domain.KeepScreenOnInteractor
import ru.starfactory.pixel.keep_screen_on.domain.KeepScreenOnInteractorImpl
import ru.starfactory.pixel.keep_screen_on.repository.KeepScreenOnRepository
import ru.starfactory.pixel.keep_screen_on.repository.KeepScreenOnRepositoryImpl
import ru.starfactory.pixel.keep_screen_on.ui.KeepScreenOnViewModel

fun Modules.featureKeepScreenOn() = DI.Module("feature-keep-screen-on") {
    bindSingleton<KeepScreenOnRepository> { KeepScreenOnRepositoryImpl(i()) }
    bindSingleton<KeepScreenOnInteractor> { KeepScreenOnInteractorImpl(i()) }
    bindProvider { KeepScreenOnViewModel(i()) }
}
