package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Usb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.PFlexVerticalGrid
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.widget.PWSettingsMenuItem
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Composable
internal fun SelectSourceView(viewModel: SelectSourceViewModel) {
    val state by viewModel.state.collectAsState()
    SelectSourceContent(state, viewModel::onSelectSource)
}

@Composable
private fun SelectSourceContent(
    state: SelectSourceViewState,
    onSelectSource: (SelectSourceViewState.Source) -> Unit
) {
    when (state) {
        SelectSourceViewState.Loading -> Unit // Loading is very fast
        is SelectSourceViewState.ShowSources -> ShowSourcesContent(state, onSelectSource)
    }
}

@Composable
private fun ShowSourcesContent(
    state: SelectSourceViewState.ShowSources,
    onSelectSource: (SelectSourceViewState.Source) -> Unit
) {
    val sources: List<SelectSourceViewState.Source> = state.sources

    Box(
        Modifier
            .fillMaxSize()
            .paddingSystemWindowInsets()
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Select source",
                Modifier.align(Alignment.CenterHorizontally),
                style = PixelTheme.typography.h6,
                fontWeight = FontWeight.W600,
            )
            PFlexVerticalGrid(
                3,
                Modifier.padding(16.dp),
            ) {
                sources.forEach {
                    SourceContent(it) { onSelectSource(it) }
                }
            }
        }

    }
}

@Composable
private fun SourceContent(
    source: SelectSourceViewState.Source,
    onClick: () -> Unit,
) {
    val icon = when (source.type) {
        SourceType.USB_SERIAL -> Icons.Default.Usb
        SourceType.DEMO -> Icons.Default.BugReport
    }

    val color = if (source.isSelected) PixelTheme.colors.primary else null

    PWSettingsMenuItem(source.name, icon, color = color, onClick = onClick)
}

