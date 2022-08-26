package ru.starfactory.pixel.settings.ui.screen.settings

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen

internal class SettingsViewModel(
    private val rootNavigation: StackNavigation<Screen>,
) : ViewModel() {
    fun onCLickClose() {
        rootNavigation.pop()
    }
}