package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState

internal sealed class DashboardViewState {
    object Loading : DashboardViewState()
    object SourceNotSelected : DashboardViewState()
    data class ShowData(val primaryState: EcuPrimaryState) : DashboardViewState()
}
