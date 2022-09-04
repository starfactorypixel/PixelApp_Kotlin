package ru.starfactory.core.uikit.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

internal val MainGradientDarkStart = Color(0xFF435159)
internal val MainGradientDarkEnd = Color(0xFF1F292E)

internal val LocalPGradients = staticCompositionLocalOf<PGradients> { throw IllegalStateException() }

@Stable
class PGradients(
    val main: Brush
) {
    companion object {
        internal val DarkColorGradient = PGradients(
            main = Brush.linearGradient(colors = listOf(MainGradientDarkStart, MainGradientDarkEnd))
        )
    }
}
