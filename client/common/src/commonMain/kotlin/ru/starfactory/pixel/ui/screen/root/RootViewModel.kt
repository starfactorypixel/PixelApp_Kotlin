package ru.starfactory.pixel.ui.screen.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.decompose.view_model.ViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance

class RootViewModel(val childStack: Value<ChildStack<Screen, ScreenInstance>>) : ViewModel()