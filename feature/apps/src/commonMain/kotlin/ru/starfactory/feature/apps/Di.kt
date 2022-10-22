package ru.starfactory.feature.apps

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.feature.apps.domain.AppsFeatureAvailabilityInteractor
import ru.starfactory.feature.apps.domain.AppsFeatureAvailabilityInteractorImpl
import ru.starfactory.feature.apps.domain.FavoriteAppsInteractor
import ru.starfactory.feature.apps.domain.FavoriteAppsInteractorImpl
import ru.starfactory.feature.apps.ui.screen.apps.AppsViewModel

fun Modules.featureApps() = DI.Module("feature-apps") {
    bindSingleton<AppsFeatureAvailabilityInteractor> { AppsFeatureAvailabilityInteractorImpl(i()) }
    bindSingleton<FavoriteAppsInteractor> { FavoriteAppsInteractorImpl() }
    bindProvider { AppsViewModel(i(), i()) }
}
