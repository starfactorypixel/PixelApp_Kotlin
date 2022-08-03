package ru.starfactory.pixel.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.starfactory.pixel.ui.screen.root.RootView

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultComponentContext = defaultComponentContext()

        setContent {
            RootView(defaultComponentContext)
        }
    }
}
