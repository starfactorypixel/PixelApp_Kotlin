package ru.starfactory.pixel.main_screen.ui.screen.main

data class MainViewState(val menuItems: List<MenuItem>) {

    enum class MenuItem {
        GENERAL,
        NAVIGATION,
        APPS,
        CHARGING
    }
}