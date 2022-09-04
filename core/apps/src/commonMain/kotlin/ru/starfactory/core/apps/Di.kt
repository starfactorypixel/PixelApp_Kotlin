package ru.starfactory.core.apps

import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.bindSingleton
import ru.starfactory.core.apps.domain.AppsInteractor
import ru.starfactory.core.di.Modules

fun Modules.coreApps() = DI.Module("core-apps") {
    bindSingleton { getAppsInteractor() }
}

internal expect fun DirectDI.getAppsInteractor(): AppsInteractor
