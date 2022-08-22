package ru.starfactory.core.navigation.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen

internal fun ComponentContext.defaultChildStack(
    navigation: StackNavigation<Screen>,
    initialScreen: Screen,
    key: String = "DefaultRouter"
): Value<ChildStack<Screen, ScreenInstance>> {
    return this.childStack(
        source = navigation,
        initialConfiguration = initialScreen,
        key = key,
        handleBackButton = true,
        childFactory = ::createChild
    )
}

@Suppress("UnusedPrivateMember")
private fun createChild(config: Screen, componentContext: ComponentContext) =
    ScreenInstance(config, componentContext)
