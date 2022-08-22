package ru.starfactory.pixel.ui.screen.root

import com.arkivanov.decompose.ComponentContext
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.core.navigation.createNavigation
import ru.starfactory.pixel.main_screen.ui.screen.MainScreen

class RootViewModel(componentContext: ComponentContext) : ViewModel() {
    val childStack = componentContext.createNavigation(MainScreen, NavigationType.ROOT)
}