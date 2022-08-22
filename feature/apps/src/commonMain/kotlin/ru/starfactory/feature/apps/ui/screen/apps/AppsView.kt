package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.starfactory.core.apps.domain.AppInfo
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun AppsView() {
    val viewModel: AppsViewModel = decomposeViewModel()
    val state by viewModel.state.collectAsState()
    AppsContent(state)
}

@Composable
private fun AppsContent(state: AppsViewState) {
    return when (state) {
        is AppsViewState.ListApps -> ListAppsContent(state)
        AppsViewState.Loading -> Unit // Loading is very fast
    }

}

@Composable
private fun ListAppsContent(state: AppsViewState.ListApps) {
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
            AppContent(it)
        }
    }
}

@Composable
private fun AppContent(app: AppInfo) {
    Row(
        Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .padding(8.dp)
    ) {
        Image(app.icon, null, Modifier.size(64.dp))
        Text(
            app.name,
            Modifier.padding(start = 12.dp).align(Alignment.CenterVertically)
        )
    }
}
