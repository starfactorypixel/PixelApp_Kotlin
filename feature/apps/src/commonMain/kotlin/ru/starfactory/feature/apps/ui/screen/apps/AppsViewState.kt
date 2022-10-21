package ru.starfactory.feature.apps.ui.screen.apps

import ru.starfactory.core.apps.domain.AppInfo

internal sealed class AppsViewState {
    object Loading : AppsViewState()
    data class ListApps(
        val favoriteApps: List<AppInfo>,
        val apps: List<AppInfo>
    ) : AppsViewState()
}
