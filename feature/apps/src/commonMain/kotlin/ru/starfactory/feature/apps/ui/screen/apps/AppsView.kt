package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.flow
import ru.starfactory.core.apps.domain.AppInfo
import ru.starfactory.core.uikit.utils.header
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun AppsView(viewModel: AppsViewModel) {
    val state by viewModel.state.collectAsState()
    AppsContent(
        state,
        viewModel::getIcon,
        viewModel::onClickApp,
        viewModel::onClickAddAppToFavorite,
        viewModel::onClickRemoveAppFromFavorite
    )
}

@Composable
private fun AppsContent(
    state: AppsViewState,
    iconLoader: suspend (AppInfo) -> ImageBitmap,
    onClickApp: (AppInfo) -> Unit,
    onClickAddAppToFavorite: (AppInfo) -> Unit,
    onClickRemoveAppFromFavorite: (AppInfo) -> Unit,
) {
    return when (state) {
        is AppsViewState.ListApps -> ListAppsContent(state, iconLoader, onClickApp, onClickAddAppToFavorite, onClickRemoveAppFromFavorite)
        AppsViewState.Loading -> Unit // Loading is very fast
    }
}

@Composable
private fun ListAppsContent(
    state: AppsViewState.ListApps,
    iconLoader: suspend (AppInfo) -> ImageBitmap,
    onClickApp: (AppInfo) -> Unit,
    onClickAddAppToFavorite: (AppInfo) -> Unit,
    onClickRemoveAppFromFavorite: (AppInfo) -> Unit,
) {
    val mainMenuInsets = LocalMainMenuInsets.current
    if (!mainMenuInsets.isPositioned) return

    val apps = state.apps
    val favoriteApps = state.favoriteApps

    LazyVerticalGrid(
        GridCells.Adaptive(minSize = 200.dp),
        Modifier
            .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        if (favoriteApps.isNotEmpty()) {
            header {
                Text("Favorites")
            }
            items(favoriteApps, key = { it.id }) {
                AppContent(it, true, iconLoader, onClickApp, onClickRemoveAppFromFavorite)
            }
            header {
                Text("Other")
            }
        }
        items(apps, key = { it.id }) {
            AppContent(it, false, iconLoader, onClickApp, onClickAddAppToFavorite)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyGridItemScope.AppContent(
    app: AppInfo,
    isFavorite: Boolean,
    iconLoader: suspend (AppInfo) -> ImageBitmap,
    onClickApp: (AppInfo) -> Unit,
    onClickFavorite: (AppInfo) -> Unit,
) {
    val haptic = LocalHapticFeedback.current
    val icon by flow { emit(iconLoader(app)) }.collectAsState(null)
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        Modifier
            .animateItemPlacement()
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = { onClickApp(app) },
                onLongClick = {
                    isMenuExpanded = true
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            )
            .padding(8.dp)
    ) {
        if (icon != null) Image(icon!!, null, Modifier.size(64.dp))
        else Spacer(Modifier.size(64.dp))

        Text(
            app.name,
            Modifier.padding(start = 12.dp).align(Alignment.CenterVertically)
        )

        DropdownMenu(
            isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
        ) {
            DropdownMenuItem(onClick = {
                isMenuExpanded = false
                onClickFavorite(app)
            }) {
                Text(text = if (isFavorite) "Remove from favorites" else "Add to favorites")
            }
        }
    }
}
