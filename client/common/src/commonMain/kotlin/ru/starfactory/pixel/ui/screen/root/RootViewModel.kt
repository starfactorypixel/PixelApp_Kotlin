package ru.starfactory.pixel.ui.screen.root

import com.arkivanov.decompose.router.stack.StackNavigation
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen

class RootViewModel : ViewModel() {
    val navigation = StackNavigation<Screen>()
}