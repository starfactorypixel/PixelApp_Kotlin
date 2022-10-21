package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.flow
import ru.starfactory.core.apps.domain.AppInfo
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun AppsView(viewModel: AppsViewModel) {
    val state by viewModel.state.collectAsState()
    AppsContent(state, viewModel::getIcon)
}

@Composable
private fun AppsContent(state: AppsViewState, iconLoader: suspend (AppInfo) -> ImageBitmap) {
    return when (state) {
        is AppsViewState.ListApps -> ListAppsContent(state, iconLoader)
        AppsViewState.Loading -> Unit // Loading is very fast
    }
}

@Composable
private fun ListAppsContent(state: AppsViewState.ListApps, iconLoader: suspend (AppInfo) -> ImageBitmap) {
    val mainMenuInsets = LocalMainMenuInsets.current
    if (!mainMenuInsets.isPositioned) return

    val apps = state.apps

    LazyVerticalGrid(
        GridCells.Adaptive(minSize = 200.dp),
        Modifier
            .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        items(apps, key = { it.id }) {
            AppContent(it, iconLoader)
        }
    }
}

@Composable
private fun AppContent(app: AppInfo, iconLoader: suspend (AppInfo) -> ImageBitmap) {
    val icon by flow { emit(iconLoader(app)) }
        .collectAsState(null)

    Row(
        Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .padding(8.dp)
    ) {
        if (icon != null) {
            Image(icon!!, null, Modifier.size(64.dp))
        } else {
            Spacer(Modifier.size(64.dp))
        }
        Text(
            app.name,
            Modifier.padding(start = 12.dp).align(Alignment.CenterVertically)
        )
    }
}
