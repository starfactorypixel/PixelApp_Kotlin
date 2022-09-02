package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowState

internal val LocalComposeWindow = compositionLocalOf<WindowState> {
    throw IllegalStateException("No local provider for LocalComposeWindow")
}

@Composable
fun FrameWindowScope.LocalComposeWindowHolder(windowState: WindowState, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalComposeWindow provides windowState, content = content)
}

@Composable
actual fun LocalConfigurationHolder(content: @Composable () -> Unit) {
    val window = LocalComposeWindow.current
    val windowSize = window.size

    val screenWidth = windowSize.width
    val screenHeight = windowSize.height

    val configuration = Configuration(
        screenWidth = screenWidth,
        screenHeight = screenHeight,
        orientation = if (screenWidth > screenHeight) {
            Configuration.Orientation.PORTRAIT
        } else {
            Configuration.Orientation.LANDSCAPE
        }
    )

    CompositionLocalProvider(LocalConfiguration provides configuration, content = content)
}

