package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import kotlinx.coroutines.flow.MutableStateFlow
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

internal class SelectSourceViewModel : ViewModel() {
    val state = MutableStateFlow(
        SelectSourceViewState.ShowSources(
            listOf(
                SelectSourceViewState.Source(SourceType.DEMO, "Demo"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo2"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo2"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo3"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo4"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo5"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo6"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo7"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo8"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo9"),
                SelectSourceViewState.Source(SourceType.DEMO, "Demo10"),
            )
        )
    )
}