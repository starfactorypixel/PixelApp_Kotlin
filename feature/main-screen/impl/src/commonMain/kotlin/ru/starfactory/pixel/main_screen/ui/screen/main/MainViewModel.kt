package ru.starfactory.pixel.main_screen.ui.screen.main

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.feature.apps.ui.screen.AppsScreen
import ru.starfactory.pixel.dashboard_screen.ui.screen.DashboardScreen
import ru.starfactory.pixel.main_screen.ui.screen.ChargingScreen
import ru.starfactory.pixel.main_screen.ui.screen.NavigatorScreen

internal class MainViewModel : ViewModel() {
    val setCurrentScreen = Channel<Screen>()
    val state = MutableStateFlow(MainViewState(MainViewState.MenuItem.values().toList()))

    fun onSelectMenuItem(menuItem: MainViewState.MenuItem) {
        viewModelScope.launch {
            val newScreen = when (menuItem) {
                MainViewState.MenuItem.GENERAL -> DashboardScreen
                MainViewState.MenuItem.NAVIGATION -> NavigatorScreen
                MainViewState.MenuItem.APPS -> AppsScreen
                MainViewState.MenuItem.CHARGING -> ChargingScreen
            }
            setCurrentScreen.send(newScreen)
        }
    }
}