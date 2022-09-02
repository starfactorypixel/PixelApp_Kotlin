package ru.starfactory.pixel.main_screen.ui.screen.main

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