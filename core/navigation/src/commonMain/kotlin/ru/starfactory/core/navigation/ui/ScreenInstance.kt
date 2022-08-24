package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import org.kodein.di.compose.subDI
import ru.starfactory.core.navigation.Screen

interface ScreenInstance {
    @Composable
    fun ScreenInstanceView()
//        subDI(diBuilder = {}) {
//            screen.ScreenView()
//        }
}
