package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.arkivanov.decompose.router.stack.StackNavigation
import ru.starfactory.core.navigation.Screen

@Suppress("TooGenericExceptionThrown")
val LocalRootNavigation = staticCompositionLocalOf<StackNavigation<Screen>> {
    throw RuntimeException("Local root navigation is not set")
}

@Composable
fun LocalRootNavigationHolder(
    navigation: StackNavigation<Screen>,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRootNavigation provides navigation) {
        CompositionLocalProvider(LocalNavigation provides navigation, content = content)
    }
}
