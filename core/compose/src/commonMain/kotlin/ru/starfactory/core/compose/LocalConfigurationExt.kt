package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.ui.unit.Dp

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
    enum class Orientation {
        PORTRAIT,
        LANDSCAPE,
    }
}