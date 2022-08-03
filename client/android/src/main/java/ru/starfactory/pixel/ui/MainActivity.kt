package ru.starfactory.pixel.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import ru.starfactory.pixel.ui.screen.root.RootScreen

class MainActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootScreen()
        }
    }
}
