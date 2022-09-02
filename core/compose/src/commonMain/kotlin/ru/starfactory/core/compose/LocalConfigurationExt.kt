package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min

@Suppress("TooGenericExceptionThrown")
val LocalConfiguration = compositionLocalOf<Configuration>(neverEqualPolicy()) {
    throw RuntimeException("Local configuration not set")
}

@Composable
expect fun LocalConfigurationHolder(content: @Composable () -> Unit)

@Immutable
data class Configuration(
    val screenWidth: Dp,
    val screenHeight: Dp,
    val orientation: Orientation,
) {

    val minSize: Dp = min(screenWidth, screenHeight)
    val maxSize: Dp = max(screenWidth, screenHeight)

    val isTablet: Boolean = minSize >= 600.dp

    enum class Orientation {
        PORTRAIT,
        LANDSCAPE,
    }
}