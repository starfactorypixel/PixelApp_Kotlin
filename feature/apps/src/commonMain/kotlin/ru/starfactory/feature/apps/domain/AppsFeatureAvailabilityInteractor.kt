package ru.starfactory.feature.apps.domain

import ru.starfactory.core.apps.domain.AppsInteractor

interface AppsFeatureAvailabilityInteractor {
    val isFeatureAvailable: Boolean
}

internal class AppsFeatureAvailabilityInteractorImpl(
    private val appsInteractor: AppsInteractor,
) : AppsFeatureAvailabilityInteractor {
    override val isFeatureAvailable: Boolean
        get() = appsInteractor.isAvailable
}