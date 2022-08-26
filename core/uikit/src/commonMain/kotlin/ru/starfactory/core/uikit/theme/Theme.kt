package ru.starfactory.core.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


@Composable
fun PixelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    val colors = PColors.DarkColorPalette
    val gradients = PGradients.DarkColorGradient

    CompositionLocalProvider(LocalPGradients provides gradients) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object PixelTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colors

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