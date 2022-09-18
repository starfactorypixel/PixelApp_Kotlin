package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.DisplaySettings
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Usb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.PFlexVerticalGrid
import ru.starfactory.core.uikit.widget.PWSettingsMenuItem
import ru.starfactory.core.uikit.widget.PWSettingsView

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

    PWSettingsView(
        "Settings",
        Modifier
            .fillMaxSize()
            .paddingSystemWindowInsets(),
        onClickClose = onClickClose
    ) {
        when (state) {
            SettingsViewState.Loading -> Unit // Loading is very fast
            is SettingsViewState.ShowSettings -> ShowSettingsContent(state, onClickMenuItem)
        }
    }
}

@Composable
private fun ShowSettingsContent(
    state: SettingsViewState.ShowSettings,
    onClickMenuItem: (SettingsViewState.MenuItem) -> Unit
) {
    PFlexVerticalGrid(minCount = 2, maxCount = 3, Modifier.padding(16.dp)) {
        state.menuItems.forEach { menuItem ->
            PWSettingsMenuItem(menuItem.text, menuItem.icon, onClick = { onClickMenuItem(menuItem) }) {
                when (menuItem.state) {
                    SettingsViewState.MenuItemState.None -> Unit
                    is SettingsViewState.MenuItemState.SwitcherState -> Switch(
                        menuItem.state.isEnabled,
                        onCheckedChange = { onClickMenuItem(menuItem) },
                        Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

private val SettingsViewState.MenuItem.text: String
    get() = when (this.id) {
        SettingsViewState.MenuItemId.DATA_SOURCE -> "Data Source"
        SettingsViewState.MenuItemId.THEME -> "Theme"
        SettingsViewState.MenuItemId.FAST_ACTION -> "Fast Actions"
        SettingsViewState.MenuItemId.LICENSE -> "License"
        SettingsViewState.MenuItemId.ABOUT -> "About"
        SettingsViewState.MenuItemId.ALWAYS_ON_DISPLAY -> "Always on display"
    }

private val SettingsViewState.MenuItem.icon: ImageVector
    get() = when (this.id) {
        SettingsViewState.MenuItemId.DATA_SOURCE -> Icons.Default.Usb
        SettingsViewState.MenuItemId.THEME -> Icons.Default.Colorize
        SettingsViewState.MenuItemId.FAST_ACTION -> Icons.Default.Close
        SettingsViewState.MenuItemId.LICENSE -> Icons.Default.Notes
        SettingsViewState.MenuItemId.ABOUT -> Icons.Default.Android
        SettingsViewState.MenuItemId.ALWAYS_ON_DISPLAY -> Icons.Default.DisplaySettings
    }
