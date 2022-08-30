package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import androidx.compose.runtime.Immutable
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

@Immutable
internal sealed class SelectSourceViewState {
    object Loading : SelectSourceViewState()

    @Immutable
    data class ShowSources(
        val sources: List<Source>,
        val isBluetoothPermissionGranted: Boolean,
    ) : SelectSourceViewState()


    @Immutable
    data class Source(
        val type: SourceType,
        val id: String,
        val name: String,
        val isSelected: Boolean,
    )
}