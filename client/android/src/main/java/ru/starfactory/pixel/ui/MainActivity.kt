package ru.starfactory.pixel.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.starfactory.pixel.ui.screen.root.RootComponent
import ru.starfactory.pixel.ui.screen.root.RootView

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultComponentContext = defaultComponentContext()
        val rootComponent = RootComponent(di, defaultComponentContext)

        setContent {
            RootView(rootComponent)
        }
    }
}
