package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.starfactory.core.apps.domain.AppInfo
import ru.starfactory.core.apps.domain.AppsInteractor
import ru.starfactory.core.decompose.view_model.ViewModel

internal class AppsViewModel(
    private val appsInteractor: AppsInteractor,
) : ViewModel() {
    private val favorites = MutableStateFlow(emptyList<String>())

    val state = combine(appsInteractor.observeApps(), favorites) { apps, favorites ->
        val sortedApps = apps.groupBy { it.id in favorites }
        // TODO это конечно максимально костыльная сортировка, нужно написать что то не за O(много)
        val favoritesSet = (sortedApps[true] ?: emptyList()).associateBy { it.id }
        val sortedFavorites = favorites.mapNotNull { favoritesSet[it] }
        AppsViewState.ListApps(
            favoriteApps = sortedFavorites,
            apps = sortedApps[false] ?: emptyList(),
        )
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, AppsViewState.Loading)

    suspend fun getIcon(app: AppInfo): ImageBitmap = appsInteractor.getIcon(app)

    fun onClickApp(app: AppInfo) = appsInteractor.launchApp(app)
    fun onClickAddAppToFavorite(app: AppInfo) {
        favorites.update { it + app.id }
    }

    fun onClickRemoveAppFromFavorite(app: AppInfo) {
        favorites.update { it - app.id }
    }
}
