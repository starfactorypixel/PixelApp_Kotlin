package ru.starfactory.feature.apps.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.feature.apps.ui.screen.apps.AppsView
import ru.starfactory.feature.apps.ui.screen.apps.AppsViewModel

@Parcelize
object AppsScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        val viewModel = decomposeViewModel<AppsViewModel>(di, componentContext.instanceKeeper)
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                AppsView(viewModel)
            }
        }
    }
}
