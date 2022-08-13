package ru.starfactory.core.decompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.arkivanov.decompose.ComponentContext

@Suppress("TooGenericExceptionThrown")
val LocalComponentContext = staticCompositionLocalOf<ComponentContext> {
    throw RuntimeException("Local navigation is not set")
}

@Composable
fun LocalComponentContextHolder(componentContext: ComponentContext, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalComponentContext provides componentContext, content = content)
}
