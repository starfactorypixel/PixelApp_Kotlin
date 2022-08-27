package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.PFlexVerticalGrid
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.widget.PWBottomMenuAction
import ru.starfactory.core.uikit.widget.PWSettingsMenuItem

@Composable
internal fun SettingsView(viewModel: SettingsViewModel) {
    SettingsContent(viewModel::onCLickClose)
}

@Composable
private fun SettingsContent(onClickClose: () -> Unit) {
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
            Column {
                Text(
                    "Settings",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = PixelTheme.typography.h6,
                    fontWeight = FontWeight.W600,
                )
                PFlexVerticalGrid(maxCount = 3, Modifier.padding(top = 16.dp)) {
                    PWSettingsMenuItem("Data Source", Icons.Default.Usb)
                    PWSettingsMenuItem("Theme", Icons.Default.Colorize)
                    PWSettingsMenuItem("Fast Actions", Icons.Default.Close)
                    PWSettingsMenuItem("License", Icons.Default.Notes)
                    PWSettingsMenuItem("About", Icons.Default.Android)
                }
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

