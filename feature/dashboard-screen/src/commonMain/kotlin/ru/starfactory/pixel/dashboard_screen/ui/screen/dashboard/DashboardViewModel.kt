package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.ui.screen.select_source.SelectSourceScreen

internal class DashboardViewModel(
    ecuSourceInteractor: EcuSourceInteractor,
    private val rootNavigation: StackNavigation<Screen>,
) : ViewModel() {
    val state = ecuSourceInteractor
        .observeSelectedSourceConnectionInteractor()
        .flatMapLatest { source ->
            source?.observePrimaryState()?.map { DashboardViewState.ShowData(it) } ?: flowOf(DashboardViewState.SourceNotSelected)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, DashboardViewState.Loading)

    fun onClickSelectSource() {
        rootNavigation.push(SelectSourceScreen)
    }
}
