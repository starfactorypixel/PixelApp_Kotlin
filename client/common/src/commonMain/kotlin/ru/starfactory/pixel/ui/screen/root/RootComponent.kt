package ru.starfactory.pixel.ui.screen.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.compose.localDI
import org.kodein.di.subDI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.defaultChildStack
import ru.starfactory.pixel.main_screen.ui.screen.MainScreen
import ru.starfactory.pixel.theming.ui.theme.ThemeViewModel

class RootComponent(di: DI, componentContext: ComponentContext) : InstanceKeeper.Instance {
    private val navigation: StackNavigation<Screen> = StackNavigation()

    private val subDi = subDI(di) {
        bindSingleton(tag = NavigationType.ROOT) { navigation }
    }

    private val childStack = componentContext.defaultChildStack(navigation, MainScreen, subDi, "root-navigation")

    val rootViewModel: RootViewModel = decomposeViewModel(subDi, componentContext) { RootViewModel(childStack) }
    val themeViewModel: ThemeViewModel = decomposeViewModel(subDi, componentContext)

    override fun onDestroy() {
        // no action
    }
}