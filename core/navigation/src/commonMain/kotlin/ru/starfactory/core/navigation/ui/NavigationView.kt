package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun NavigationContentView(childStack: Value<ChildStack<Screen, ScreenInstance>>) {
    Children(
        childStack,
        animation = animation()
    ) { childCreated ->
        childCreated.instance.ScreenInstanceView()
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun animation(): StackAnimation<Screen, ScreenInstance> =
    stackAnimation { _, _, _ -> scale() + fade() }
