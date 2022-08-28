package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.Source

internal class SelectSourceViewModel(
    private val ecuSourceInteractor: EcuSourceInteractor,
) : ViewModel() {
    val state =
        combine(
            ecuSourceInteractor.observeSources(),
            ecuSourceInteractor.observeSelectedSource(),
        ) { sources, selectedSource ->
            sources.map { it.toUiSource(it == selectedSource) }
        }
            .map { SelectSourceViewState.ShowSources(it) }
            .stateIn(viewModelScope, started = SharingStarted.Eagerly, SelectSourceViewState.Loading)

    fun onSelectSource(source: SelectSourceViewState.Source) {
        viewModelScope.launch {
            ecuSourceInteractor.selectSource(source.id)
        }
    }
}

private fun Source.toUiSource(isSelected: Boolean) = SelectSourceViewState.Source(sourceType, id, name, isSelected)
