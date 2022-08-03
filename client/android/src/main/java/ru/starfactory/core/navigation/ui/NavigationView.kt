package ru.starfactory.core.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen

@Composable
fun NavigationContentView(childStack: Value<ChildStack<Screen, ScreenInstance>>) {
    Children(
        childStack
    ) { childCreated ->
        childCreated.instance.ScreenInstanceView()
    }
}
