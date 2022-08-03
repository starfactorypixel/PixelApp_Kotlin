package ru.starfactory.pixel.ui.screen.main_menu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.push
import ru.starfactory.core.navigation.ui.LocalNavigation
import ru.starfactory.pixel.ui.screen.SettingsScreen

@Composable
fun MainMenuView() {
    val navigation = LocalNavigation.current
    MainMenuContent(onClickSettings = { navigation.push(SettingsScreen) })
}

@Composable
fun MainMenuContent(
    onClickSettings: () -> Unit = {}
) {
    Button(onClick = onClickSettings) {
        Text(text = "Settings")
    }
}