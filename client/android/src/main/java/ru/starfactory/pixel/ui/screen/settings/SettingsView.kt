package ru.starfactory.pixel.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.pixel.theming.domain.Theme
import ru.starfactory.pixel.ui.theme.PixelTheme


@Composable
fun SettingsView(viewModel: SettingsViewModel = decomposeViewModel()) {
    val state by viewModel.state.collectAsState()
    SettingsContent(
        state,
        onChangeTheme = { viewModel.onChangeTheme(it) }
    )
}

@Composable
private fun SettingsContent(
    state: SettingsViewState,
    onChangeTheme: (Theme) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable { expanded = true }
    ) {
        Row(Modifier.padding(16.dp, 8.dp)) {
            Text("Theme:", Modifier.weight(1f))
            Box {
                Text(text = state.theme.name)
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    Theme.values().forEach { theme ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                onChangeTheme(theme)
                            }
                        ) {
                            Text(text = theme.name)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsContentPreview() {
    PixelTheme(darkTheme = true) {
        SettingsContent(SettingsViewState(Theme.SYSTEM))
    }
}