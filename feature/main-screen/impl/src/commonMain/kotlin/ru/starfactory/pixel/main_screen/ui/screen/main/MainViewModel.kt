package ru.starfactory.pixel.main_screen.ui.screen.main

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.feature.apps.domain.AppsFeatureAvailabilityInteractor
import ru.starfactory.feature.apps.ui.screen.AppsScreen
import ru.starfactory.pixel.dashboard_screen.ui.screen.DashboardScreen
import ru.starfactory.pixel.main_screen.ui.screen.ChargingScreen
import ru.starfactory.pixel.main_screen.ui.screen.NavigatorScreen
import ru.starfactory.pixel.settings.ui.screen.settings.SettingsScreen

internal class MainViewModel(
    private val appsFeatureAvailabilityInteractor: AppsFeatureAvailabilityInteractor,
    private val rootNavigation: StackNavigation<Screen>
) : ViewModel() {
    val navigation = StackNavigation<Screen>()

    val state = MutableStateFlow(
        MainViewState(
            MainViewState.MenuItem.values().filter(this::isFeatureAvailable),
            selectedMenuItem = MainViewState.MenuItem.GENERAL,
        )
    )

    fun onSelectMenuItem(menuItem: MainViewState.MenuItem) {
        viewModelScope.launch {
            state.update {
                it.copy(selectedMenuItem = menuItem)
            }

            val newScreen = when (menuItem) {
                MainViewState.MenuItem.GENERAL -> DashboardScreen
                MainViewState.MenuItem.NAVIGATION -> NavigatorScreen
                MainViewState.MenuItem.APPS -> AppsScreen
                MainViewState.MenuItem.CHARGING -> ChargingScreen
            }
            navigation.replaceCurrent(newScreen)
        }
    }

    fun onClickSettings() {
        rootNavigation.push(SettingsScreen)
    }

    private fun isFeatureAvailable(menuItem: MainViewState.MenuItem): Boolean {
        return when (menuItem) {
            MainViewState.MenuItem.GENERAL,
            MainViewState.MenuItem.NAVIGATION,
            MainViewState.MenuItem.CHARGING -> true
            MainViewState.MenuItem.APPS -> appsFeatureAvailabilityInteractor.isFeatureAvailable
        }
    }
}