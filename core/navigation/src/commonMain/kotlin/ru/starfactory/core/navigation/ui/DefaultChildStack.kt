package ru.starfactory.core.navigation.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import org.kodein.di.DI
import ru.starfactory.core.navigation.Screen

fun ComponentContext.defaultChildStack(
    navigation: StackNavigation<Screen>,
    initialScreen: Screen,
    di: DI,
    key: String = "DefaultRouter"
): Value<ChildStack<Screen, ScreenInstance>> {
    return this.childStack(
        source = navigation,
        initialConfiguration = initialScreen,
        key = key,
        handleBackButton = true,
        childFactory = { config, context -> createChild(di, config, context) }
    )
}

@Suppress("UnusedPrivateMember")
private fun createChild(di: DI, config: Screen, componentContext: ComponentContext): ScreenInstance {
    return config.createScreenInstance(di, componentContext)
}
