package ru.starfactory.pixel.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.starfactory.pixel.ui.screen.main.MainScreen
import ru.starfactory.pixel.ui.theme.PixelTheme

@Composable
fun RootScreen() {
    PixelTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen()
        }
    }
}