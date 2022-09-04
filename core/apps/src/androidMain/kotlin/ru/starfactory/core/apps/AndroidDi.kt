package ru.starfactory.core.apps

import org.kodein.di.DirectDI
import org.kodein.di.instance
import ru.starfactory.core.apps.domain.AndroidAppsInteractor
import ru.starfactory.core.apps.domain.AppsInteractor

internal actual fun DirectDI.getAppsInteractor(): AppsInteractor {
    return AndroidAppsInteractor(instance())
}
