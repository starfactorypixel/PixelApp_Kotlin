package ru.starfactory.pixel.main_screen.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.subDI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.NavigationType
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.core.navigation.ui.defaultChildStack
import ru.starfactory.pixel.dashboard_screen.ui.screen.DashboardScreen
import ru.starfactory.pixel.main_screen.ui.screen.main.MainView
import ru.starfactory.pixel.main_screen.ui.screen.main.MainViewModel

@Parcelize
object MainScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        val viewModel: MainViewModel = decomposeViewModel(di, componentContext.instanceKeeper)

        val navigation = viewModel.navigation

        val subDi = subDI(di) {
            bindSingleton(tag = NavigationType.CHILD) { navigation }
        }

        val childStack = componentContext.defaultChildStack(navigation, DashboardScreen, subDi)

        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                MainView(viewModel, childStack)
            }
        }
    }
}

@Parcelize
internal object NavigatorScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                Text("Navigator screen in development")
            }
        }
    }
}

@Parcelize
internal object ChargingScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                Text("Charging screen in development")
            }
        }
    }
}
