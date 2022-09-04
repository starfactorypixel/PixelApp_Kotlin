package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance

@Parcelize
object SettingsScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        val viewModel: SettingsViewModel = decomposeViewModel(di, componentContext.instanceKeeper)
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                SettingsView(viewModel)
            }
        }
    }
}
