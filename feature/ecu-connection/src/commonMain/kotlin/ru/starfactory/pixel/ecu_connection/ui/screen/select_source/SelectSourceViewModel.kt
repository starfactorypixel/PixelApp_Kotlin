package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.Source

internal class SelectSourceViewModel(
    private val ecuSourceInteractor: EcuSourceInteractor,
) : ViewModel() {
    val state = ecuSourceInteractor.observeSources()
        .map { devices -> devices.toUiSources() }
        .map { SelectSourceViewState.ShowSources(it) }
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, SelectSourceViewState.Loading)

}

private fun List<Source>.toUiSources() = map { it.toSource() }
private fun Source.toSource() = when (this) {
    Source.Demo -> SelectSourceViewState.Source(sourceType, "demo", "demo")
    is Source.Serial -> SelectSourceViewState.Source(sourceType, id, name)
}