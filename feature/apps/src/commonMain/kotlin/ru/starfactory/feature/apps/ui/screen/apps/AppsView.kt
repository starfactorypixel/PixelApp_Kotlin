package ru.starfactory.feature.apps.ui.screen.apps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun AppsView() {
    AppsContent()
}

@Composable
private fun AppsContent() {
    val mainMenuInsets = LocalMainMenuInsets.current
    if (!mainMenuInsets.isPositioned) return

    val apps = (0..30).map {
        AppState("App #$it")
    }
    LazyVerticalGrid(
        GridCells.Adaptive(minSize = 200.dp),
        Modifier
            .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        items(apps, key = { it.name }) {
            AppContent(it)
        }
    }
}

@Composable
private fun AppContent(app: AppState) {
    Card(Modifier.height(100.dp)) {
        Box(Modifier.fillMaxSize()) {
            Text(app.name, Modifier.align(Alignment.Center))
        }
    }
}

private data class AppState(val name: String)