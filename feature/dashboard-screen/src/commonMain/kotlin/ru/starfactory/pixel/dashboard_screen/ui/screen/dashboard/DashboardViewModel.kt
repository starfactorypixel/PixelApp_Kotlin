package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuDefaultSourceConnectionInteractor

internal class DashboardViewModel(
    ecuDefaultSourceConnectionInteractor: EcuDefaultSourceConnectionInteractor,
) : ViewModel() {
    val state = ecuDefaultSourceConnectionInteractor.observePrimaryState()
        .map { DashboardViewState.ShowData(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, DashboardViewState.Loading)
}
