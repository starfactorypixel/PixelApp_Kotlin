package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import ru.startfactory.core.decompose.LocalComponentContextHolder
import ru.starfactory.core.navigation.Screen

class ScreenInstance(private val screen: Screen, private val componentContext: ComponentContext) {
    @Composable
    internal fun ScreenInstanceView() {
        LocalComponentContextHolder(componentContext) {
            screen.ScreenView()
        }
    }
}
