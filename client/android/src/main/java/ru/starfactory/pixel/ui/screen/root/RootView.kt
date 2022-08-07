package ru.starfactory.pixel.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.getValue
import ru.starfactory.core.decompose.LocalComponentContextHolder
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.LocalRootNavigationHolder
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.core.navigation.ui.defaultChildStack
import ru.starfactory.pixel.ui.screen.MainScreen
import ru.starfactory.pixel.ui.screen.UsbSerialScreen
import ru.starfactory.pixel.ui.theme.PixelTheme

@Composable
fun RootView(componentContext: ComponentContext) {
    val navigation = remember { StackNavigation<Screen>() }
    val childStack = componentContext.defaultChildStack(navigation, MainScreen)

    LocalComponentContextHolder(componentContext) {
        LocalRootNavigationHolder(navigation) {
            PixelTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationContentView(childStack)
                }
            }
        }
    }
}
