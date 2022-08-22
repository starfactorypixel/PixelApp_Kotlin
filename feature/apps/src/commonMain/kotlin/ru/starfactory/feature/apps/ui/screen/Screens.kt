package ru.starfactory.feature.apps.ui.screen

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.starfactory.core.navigation.Screen
import ru.starfactory.feature.apps.ui.screen.apps.AppsView

@Parcelize
object AppsScreen : Screen {
    @Composable
    override fun ScreenView() {
        AppsView()
    }
}

