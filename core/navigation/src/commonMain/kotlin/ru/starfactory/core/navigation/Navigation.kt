package ru.starfactory.core.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.core.navigation.ui.defaultChildStack


fun ComponentContext.createNavigation(
    initialScreen: Screen,
    key: Any
): Value<ChildStack<Screen, ScreenInstance>> {
    val navigation = StackNavigation<Screen>()
    val holder = NavigationHolder(navigation)
    instanceKeeper.put(key.toString(), holder)
    return defaultChildStack(navigation, initialScreen, "$key-child_stack")
}

fun ComponentContext.getNavigation(key: Any): StackNavigation<Screen> {
    return (instanceKeeper.get(key.toString()) as NavigationHolder).navigation
}

private data class NavigationHolder(val navigation: StackNavigation<Screen>) : InstanceKeeper.Instance {
    override fun onDestroy() {
        // no action
    }
}