package ru.starfactory.pixel.ui.screen.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.starfactory.core.compose.LocalConfigurationHolder
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.core.permission.ui.PermissionView
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.pixel.theming.ui.theme.ThemeView

@Composable
fun RootView(rootComponent: RootComponent) {

    LocalConfigurationHolder {

        PermissionView(rootComponent.permissionViewModel)

        ThemeView(rootComponent.themeViewModel) {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(PixelTheme.gradients.main)
                ) {
                    NavigationContentView(rootComponent.childStack)
                }
            }
        }
    }
}
