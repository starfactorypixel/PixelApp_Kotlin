package ru.starfactory.pixel.settings.ui.screen.settings

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelize
import org.kodein.di.DI
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance

@Parcelize
object SettingsScreen : Screen {
    override fun createScreenInstance(di: DI, componentContext: ComponentContext): ScreenInstance {
        return object : ScreenInstance {
            @Composable
            override fun ScreenInstanceView() {
                SettingsView()
            }
        }
    }
}