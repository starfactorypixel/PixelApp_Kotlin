package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.FrameWindowScope

internal val LocalComposeWindow = compositionLocalOf<ComposeWindow> {
    throw IllegalStateException("No local provider for LocalComposeWindow")
}

@Composable
fun FrameWindowScope.LocalComposeWindowHolder(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalComposeWindow provides window, content = content)
}

@Composable
actual fun LocalConfigurationHolder(content: @Composable () -> Unit) {
    val windowInfo = LocalComposeWindow.current
    val density = LocalDensity.current
    CompositionLocalProvider(LocalConfiguration provides JvmConfiguration(windowInfo, density), content = content)
}

private class JvmConfiguration(
    private val configuration: ComposeWindow,
    private val density: Density,
) : Configuration {
    override val screenWidth: Dp
        get() = with(density) { configuration.width.toDp() }

    override val screenHeight: Dp
        get() = with(density) { configuration.height.toDp() }
    override val orientation: Configuration.Orientation
        get() = if (screenWidth > screenHeight) {
            Configuration.Orientation.PORTRAIT
        } else {
            Configuration.Orientation.LANDSCAPE
        }
}