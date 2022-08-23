package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import org.kodein.di.compose.subDI
import ru.starfactory.core.navigation.Screen

class ScreenInstance(private val screen: Screen, private val componentContext: ComponentContext) {
    @Composable
    internal fun ScreenInstanceView() {
        subDI(diBuilder = {}) {
            screen.ScreenView()
        }
    }
}
