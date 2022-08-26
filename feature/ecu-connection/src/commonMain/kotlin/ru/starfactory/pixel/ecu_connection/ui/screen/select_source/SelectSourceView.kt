package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.uikit.layout.FlexVerticalGrid
import ru.starfactory.core.uikit.view.POutlinedCard
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Composable
internal fun SelectSourceView(viewModel: SelectSourceViewModel) {
    val state by viewModel.state.collectAsState()
    SelectSourceContent(state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SelectSourceContent(state: SelectSourceViewState.ShowSources) {
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
            FlexVerticalGrid(
                3,
                Modifier
                    .padding(16.dp),
                verticalSpacing = 16.dp,
                horizontalSpacing = 16.dp
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
    POutlinedCard(
        Modifier
            .defaultMinSize(minWidth = 180.dp, minHeight = 100.dp)
            .clickable { }
    ) {
        when (source.type) {
            SourceType.DEMO -> DefaultSourceContent(Icons.Default.BugReport, source.name)
        }
    }
}

@Composable
private fun DefaultSourceContent(
    icon: ImageVector,
    text: String
) {
    Box(
        Modifier
            .padding(16.dp, 8.dp)
    ) {
        Row(
            Modifier
                .align(Alignment.Center)
        ) {
            Icon(
                icon,
                null,
                Modifier
                    .align(Alignment.CenterVertically)
            )
            Text(
                text,
                Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
