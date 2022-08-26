package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.view.POutlinedFloatingActionButton

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
            Text("Settings")
        }
        BottomAction(
            "Close",
            Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            onClickClose
        )
    }
}

@Composable
private fun BottomAction(text: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(modifier) {
        POutlinedFloatingActionButton(onClick = onClick) {
            Icon(Icons.Default.Settings, null, Modifier.size(32.dp))
        }
        Text(
            text,
            Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}