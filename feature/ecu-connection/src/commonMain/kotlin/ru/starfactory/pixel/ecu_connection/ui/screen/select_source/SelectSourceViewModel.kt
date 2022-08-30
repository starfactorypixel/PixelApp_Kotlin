package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.serial.bluetooth.domain.BluetoothSerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.Source

internal class SelectSourceViewModel(
    private val ecuSourceInteractor: EcuSourceInteractor,
    private val bluetoothSerialInteractor: BluetoothSerialInteractor,
) : ViewModel() {
    val state = combine(
        ecuSourceInteractor.observeSources(),
        ecuSourceInteractor.observeSelectedSource(),
        bluetoothSerialInteractor.observeIsPermissionGranted(),
    ) { sources, selectedSource, isBluetoothPermissionGranted ->
        val uiSources = sources.map { it.toUiSource(it == selectedSource) }
        SelectSourceViewState.ShowSources(uiSources, isBluetoothPermissionGranted)
    }
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, SelectSourceViewState.Loading)

    fun onSelectSource(source: SelectSourceViewState.Source) {
        viewModelScope.launch {
            ecuSourceInteractor.selectSource(source.id)
        }
    }

    fun onRequestBluetoothPermission() {
        viewModelScope.launch {
            bluetoothSerialInteractor.requestPermission()
        }
    }
}

private fun Source.toUiSource(isSelected: Boolean) = SelectSourceViewState.Source(sourceType, id, name, isSelected)
