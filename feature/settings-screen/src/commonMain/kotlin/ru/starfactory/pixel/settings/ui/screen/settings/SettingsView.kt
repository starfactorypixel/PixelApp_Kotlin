package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.PFlexVerticalGrid
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.widget.PWBottomMenuAction
import ru.starfactory.core.uikit.widget.PWSettingsMenuItem

@Composable
internal fun SettingsView(viewModel: SettingsViewModel) {
    val state by viewModel.state.collectAsState()
    SettingsContent(state, viewModel::onCLickClose, viewModel::onClickMenuItem)
}

@Composable
private fun SettingsContent(
    state: SettingsViewState,
    onClickClose: () -> Unit,
    onClickMenuItem: (SettingsViewState.MenuItem) -> Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .paddingSystemWindowInsets()
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
        ) {
            when (state) {
                SettingsViewState.Loading -> Unit // Loading is very fast
                is SettingsViewState.ShowSettings -> ShowSettingsContent(state, onClickMenuItem)
            }
        }

        PWBottomMenuAction(
            text = "Close",
            icon = Icons.Default.Close,
            Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            onClick = onClickClose,
            borderColor = PixelTheme.colors.error
        )
    }
}

@Composable
private fun ShowSettingsContent(
    state: SettingsViewState.ShowSettings,
    onClickMenuItem: (SettingsViewState.MenuItem) -> Unit
) {
    Column {
        Text(
            "Settings",
            Modifier.align(Alignment.CenterHorizontally),
            style = PixelTheme.typography.h6,
            fontWeight = FontWeight.W600,
        )
        PFlexVerticalGrid(maxCount = 3, Modifier.padding(16.dp)) {
            state.menuItems.forEach {
                PWSettingsMenuItem(it.text, it.icon, onClick = { onClickMenuItem(it) })
            }
        }
    }
}

private val SettingsViewState.MenuItem.text: String
    get() = when (this) {
        SettingsViewState.MenuItem.DATA_SOURCE -> "Data Source"
        SettingsViewState.MenuItem.THEME -> "Theme"
        SettingsViewState.MenuItem.FAST_ACTION -> "Fast Actions"
        SettingsViewState.MenuItem.LICENSE -> "License"
        SettingsViewState.MenuItem.ABOUT -> "About"
    }

private val SettingsViewState.MenuItem.icon: ImageVector
    get() = when (this) {
        SettingsViewState.MenuItem.DATA_SOURCE -> Icons.Default.Usb
        SettingsViewState.MenuItem.THEME -> Icons.Default.Colorize
        SettingsViewState.MenuItem.FAST_ACTION -> Icons.Default.Close
        SettingsViewState.MenuItem.LICENSE -> Icons.Default.Notes
        SettingsViewState.MenuItem.ABOUT -> Icons.Default.Android
    }


