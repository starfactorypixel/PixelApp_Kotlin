package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.pixel.ecu_connection.domain.source.Source
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Composable
internal fun SelectSourceView() {
    SelectSourceContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SelectSourceContent() {
    val sources: Map<SourceType, List<Source>> = mapOf(
        SourceType.DEMO to listOf(Source.Demo)
    )

    LazyColumn(
        Modifier
            .fillMaxSize()
            .paddingSystemWindowInsets(),
    ) {
        sources.forEach { (sourcesType, sources) ->
            stickyHeader(key = sourcesType) {
                SourceHeader(sourcesType)
            }
            items(sources) { source ->
                SourceContent(source)
            }
        }
    }
}

@Composable
private fun SourceHeader(sourceType: SourceType) {
    Text(
        sourceType.name,
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary.copy(alpha = 0.15f))
            .padding(16.dp, 8.dp)
    )
}

@Composable
private fun SourceContent(source: Source) {
    return when (source) {
        Source.Demo -> DemoSourceContent()
    }
}

@Composable
private fun DemoSourceContent() {
    Text(
        "Demo source",
        Modifier
            .fillMaxSize()
            .clickable { }
            .padding(16.dp, 8.dp)
    )
}
