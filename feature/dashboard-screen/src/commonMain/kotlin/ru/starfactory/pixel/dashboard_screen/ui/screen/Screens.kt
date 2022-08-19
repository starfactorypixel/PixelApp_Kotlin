package ru.starfactory.pixel.dashboard_screen.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard.DashboardView

@Parcelize
object DashboardScreen : Screen {
    @Composable
    override fun ScreenView() {
        DashboardView()
    }
}