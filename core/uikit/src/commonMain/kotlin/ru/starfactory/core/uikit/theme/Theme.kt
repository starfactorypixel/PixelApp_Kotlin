package ru.starfactory.core.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
@Suppress("UnusedPrivateMember") // TODO Sumin
fun PixelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    val colors = PColors.DarkColorPalette
    val gradients = PGradients.DarkColorGradient

    CompositionLocalProvider(
        LocalPColors provides colors,
        LocalPGradients provides gradients,
    ) {
        MaterialTheme(
            colors = colors.toColors(),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object PixelTheme {
    val colors: PColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val gradients: PGradients
        @Composable
        @ReadOnlyComposable
        get() = LocalPGradients.current
}
