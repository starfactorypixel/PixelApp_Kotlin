package ru.starfactory.pixel.main_screen.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
internal object GeneralScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("General screen in development")
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
internal object AppsScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("Apps screen in development")
    }
}

@Parcelize
internal object ChargingScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("Charging screen in development")
    }
}