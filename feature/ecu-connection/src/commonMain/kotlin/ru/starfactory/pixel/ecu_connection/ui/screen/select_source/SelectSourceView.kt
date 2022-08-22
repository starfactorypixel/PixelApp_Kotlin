package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.runtime.Composable
import ru.starfactory.pixel.ecu_connection.domain.source.Source
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Composable
internal fun SelectSourceView() {
    SelectSourceContent()
}

@Composable
private fun SelectSourceContent() {
    val sources: Map<SourceType, List<Source>> = mapOf(
        SourceType.DEMO to listOf(Source.Demo)
    )
}

@Composable
private fun SourceHeader(sourceType: SourceType) {

}

@Composable
private fun SourceContent(source: Source) {
    return when (source) {
        Source.Demo -> DemoSourceContent()
    }
}

@Composable
private fun DemoSourceContent() {

}
