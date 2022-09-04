package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable

interface ScreenInstance {
    @Composable
    fun ScreenInstanceView()
//        subDI(diBuilder = {}) {
//            screen.ScreenView()
//        }
}
