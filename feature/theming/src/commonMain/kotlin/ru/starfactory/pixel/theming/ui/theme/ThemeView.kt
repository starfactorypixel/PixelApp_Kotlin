package ru.starfactory.pixel.theming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.pixel.theming.domain.Theme

@Composable
fun ThemeView(viewModel: ThemeViewModel, content: @Composable () -> Unit) {
    val state by viewModel.state.collectAsState()

    // we can't move isSystemInDarkTheme() into when statement, because its produce internal compose crash
    val isSystemDark = isSystemInDarkTheme()

    val isDark = when (state) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        Theme.SYSTEM -> isSystemDark
        null -> return
    }
    PixelTheme(darkTheme = isDark, content)
}
