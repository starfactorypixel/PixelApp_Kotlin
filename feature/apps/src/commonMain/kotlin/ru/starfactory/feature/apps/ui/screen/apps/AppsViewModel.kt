package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.apps.domain.AppInfo
import ru.starfactory.core.apps.domain.AppsInteractor
import ru.starfactory.core.decompose.view_model.ViewModel

internal class AppsViewModel(
    private val appsInteractor: AppsInteractor,
) : ViewModel() {
    val state = flow { emit(appsInteractor.getApps()) }
        .map { AppsViewState.ListApps(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, AppsViewState.Loading)

    suspend fun getIcon(app: AppInfo): ImageBitmap = appsInteractor.getIcon(app)

    fun onClickApp(app: AppInfo) = appsInteractor.launchApp(app)
}
