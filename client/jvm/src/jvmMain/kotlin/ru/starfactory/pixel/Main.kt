package ru.starfactory.pixel

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.kodein.di.DI
import org.kodein.di.compose.withDI
import ru.starfactory.core.compose.LocalComposeWindowHolder
import ru.starfactory.core.di.Modules
import ru.starfactory.pixel.ui.screen.root.RootView

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val di = DI.lazy {
        importOnce(Modules.mainCommonModule())
    }

    initApp(di)

    val lifecycle = LifecycleRegistry()
    val backDispatcher = BackDispatcher()
    val defaultComponentContext = DefaultComponentContext(lifecycle, backHandler = backDispatcher)

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = this::exitApplication,
            state = windowState,
            title = "Pixel App",
            onKeyEvent = {
                if (it.key == Key.Escape) {
                    backDispatcher.back()
                } else {
                    false
                }
            }
        ) {
            LocalComposeWindowHolder {
                withDI(di) {
                    DesktopScrollbarStyle {
                        RootView(defaultComponentContext)
                    }
                }
            }
        }
    }
}

@Composable
private fun DesktopScrollbarStyle(
    scrollbarStyle: ScrollbarStyle = defaultScrollbarStyle(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalScrollbarStyle provides scrollbarStyle, content = content)
}