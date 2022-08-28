package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Usb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.PFlexVerticalGrid
import ru.starfactory.core.uikit.widget.PWSettingsMenuItem
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Composable
internal fun SelectSourceView(viewModel: SelectSourceViewModel) {
    val state by viewModel.state.collectAsState()
    SelectSourceContent(state)
}

@Composable
private fun SelectSourceContent(state: SelectSourceViewState) {
    when (state) {
        SelectSourceViewState.Loading -> Unit // Loading is very fast
        is SelectSourceViewState.ShowSources -> ShowSourcesContent(state)
    }
}

@Composable
private fun ShowSourcesContent(state: SelectSourceViewState.ShowSources) {
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
            PFlexVerticalGrid(
                3,
                Modifier.padding(16.dp),
            ) {
                sources.forEach {
                    SourceContent(it)
                }
            }
        }

    }
}

@Composable
private fun SourceContent(source: SelectSourceViewState.Source) {
    when (source.type) {
        SourceType.USB_SERIAL -> PWSettingsMenuItem(source.name, Icons.Default.Usb)
        SourceType.DEMO -> PWSettingsMenuItem(source.name, Icons.Default.BugReport)
    }
}

