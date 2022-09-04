package ru.starfactory.pixel.main_screen.ui.main_menu_insets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class MainMenuInsets(
    val positionInRoot: DpOffset = DpOffset(0.dp, 0.dp),
    val size: DpSize = DpSize(0.dp, 0.dp),
    val isPositioned: Boolean = false
)

val LocalMainMenuInsets = compositionLocalOf<MainMenuInsets> { MainMenuInsets() }

@Composable
fun LocalMainMenuInsetsHolder(mainMenuInsets: MainMenuInsets, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalMainMenuInsets provides mainMenuInsets, content = content)
}

@Composable
fun WithLocalMainMenuInsets(content: @Composable (MainMenuInsets) -> Unit) {
    val mainMenuInsets = LocalMainMenuInsets.current
    if (mainMenuInsets.isPositioned) {
        content(mainMenuInsets)
    }
}
