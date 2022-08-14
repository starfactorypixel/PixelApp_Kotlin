package ru.starfactory.pixel.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen


@Parcelize
object MainScreen : Screen {
    @Composable
    override fun ScreenView() {
        Text("Hello multiplatform")
        // MainView()
    }
}
