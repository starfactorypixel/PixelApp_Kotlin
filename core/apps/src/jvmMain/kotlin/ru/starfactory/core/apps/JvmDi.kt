package ru.starfactory.core.apps

import org.kodein.di.DirectDI
import ru.starfactory.core.apps.domain.AppsInteractor
import ru.starfactory.core.apps.domain.JvmAppsInteractor

internal actual fun DirectDI.getAppsInteractor(): AppsInteractor {
    return JvmAppsInteractor()
}
