package ru.starfactory.pixel.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.ui.screen.main.MainView


@Parcelize
object MainScreen : Screen {
    @Composable
    override fun ScreenView() {
         MainView()
    }
}