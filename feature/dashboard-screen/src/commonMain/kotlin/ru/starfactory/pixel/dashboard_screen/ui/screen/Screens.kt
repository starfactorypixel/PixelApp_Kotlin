package ru.starfactory.pixel.dashboard_screen.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard.DashboardView
import ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard.DashboardViewModel

@Parcelize
object DashboardScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        val viewModel: DashboardViewModel = decomposeViewModel(di, componentContext.instanceKeeper)
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                DashboardView(viewModel)
            }
        }
    }
}