package ru.starfactory.feature.apps

import org.kodein.di.DI
import org.kodein.di.bindProvider
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.feature.apps.ui.screen.apps.AppsViewModel

fun Modules.featureApps() = DI.Module("feature-apps") {
    bindProvider { AppsViewModel(i()) }
}