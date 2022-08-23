package ru.starfactory.pixel.ecu_connection.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.ecu_connection.ui.screen.select_source.SelectSourceView

@Parcelize
object SelectSourceScreen : Screen {
    @Composable
    override fun ScreenView() {
        SelectSourceView()
    }
}
