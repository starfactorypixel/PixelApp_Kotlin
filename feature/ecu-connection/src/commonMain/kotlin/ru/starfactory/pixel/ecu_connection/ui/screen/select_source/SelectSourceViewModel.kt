package ru.starfactory.pixel.ecu_connection.ui.screen.select_source

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.SourceType

internal class SelectSourceViewModel(
    private val serialInteractor: SerialInteractor,
) : ViewModel() {
    val state = serialInteractor.observeSerialDevices()
        .map { devices ->
            devices.toSources() +
                    listOf(
                        SelectSourceViewState.Source(SourceType.DEMO, "Demo", "Demo"),
                        SelectSourceViewState.Source(SourceType.DEMO, "Demo2", "Demo2"),
                        SelectSourceViewState.Source(SourceType.DEMO, "Demo2", "Demo3"),
                    )
        }
        .map { SelectSourceViewState.ShowSources(it) }
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, SelectSourceViewState.Loading)

}

private fun List<SerialDevice>.toSources() = map { it.toSource() }
private fun SerialDevice.toSource() = SelectSourceViewState.Source(type.toSourceType(), id.toString(), name)
private fun SerialDeviceType.toSourceType(): SourceType = when (this) {
    SerialDeviceType.USB -> SourceType.USB_SERIAL
}