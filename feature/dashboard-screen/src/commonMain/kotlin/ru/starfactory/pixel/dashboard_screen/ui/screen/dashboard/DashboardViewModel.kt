package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.core.navigation.getNavigation
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuConnectionInteractor
import ru.starfactory.pixel.ecu_connection.ui.screen.SelectSourceScreen

internal class DashboardViewModel(
    ecuConnectionInteractor: EcuConnectionInteractor,
    context: ComponentContext,
) : ViewModel() {
    val state = ecuConnectionInteractor.observePrimaryState()
        .map { DashboardViewState.ShowData(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, DashboardViewState.Loading)

    private val rootNavigation = context.getNavigation(NavigationType.ROOT)

    fun onClickSettings() {
        rootNavigation.push(SelectSourceScreen)
    }
}