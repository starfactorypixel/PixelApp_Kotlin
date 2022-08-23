package ru.starfactory.pixel.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.starfactory.core.compose.LocalConfigurationHolder
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.pixel.theming.ui.theme.ThemeView

@Composable
fun RootView(rootComponent: RootComponent) {

    LocalConfigurationHolder {

        // PermissionView()

        ThemeView(rootComponent.themeViewModel) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NavigationContentView(rootComponent.rootViewModel.childStack)
            }
        }
    }
}
