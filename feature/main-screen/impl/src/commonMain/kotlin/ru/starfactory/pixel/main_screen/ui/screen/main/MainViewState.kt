package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Navigation
import ru.starfactory.pixel.main_screen.ui.widged.PVerticalMenuItem

data class MainViewState(
    val menuItems: List<MenuItem>,
    val selectedMenuItem: MenuItem,
) {

    enum class MenuItem {
        GENERAL,
        NAVIGATION,
        APPS,
        CHARGING
    }
}

internal fun List<MainViewState.MenuItem>.toPMenuItem(): List<PVerticalMenuItem<MainViewState.MenuItem>> = this.map { it.toPMenuItem() }

internal fun MainViewState.MenuItem.toPMenuItem(): PVerticalMenuItem<MainViewState.MenuItem> = when (this) {
    MainViewState.MenuItem.GENERAL -> PVerticalMenuItem(id = this, Icons.Default.DirectionsCar, "General")
    MainViewState.MenuItem.NAVIGATION -> PVerticalMenuItem(id = this, Icons.Default.Navigation, "Navigation")
    MainViewState.MenuItem.APPS -> PVerticalMenuItem(id = this, Icons.Default.Apps, "Apps")
    MainViewState.MenuItem.CHARGING -> PVerticalMenuItem(id = this, Icons.Default.BatteryChargingFull, "Charging")
}
