package ru.starfactory.pixel.main_screen.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.main_screen.ui.screen.main.MainView

@Parcelize
object MainScreen : Screen {
    @Composable
    override fun ScreenView() {
        MainView()
    }
}

@Parcelize
internal object NavigatorScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("Navigator screen in development")
    }
}

@Parcelize
internal object ChargingScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("Charging screen in development")
    }
}