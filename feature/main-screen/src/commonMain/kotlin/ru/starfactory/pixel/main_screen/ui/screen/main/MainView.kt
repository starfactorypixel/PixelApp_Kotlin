package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMainMenu
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMenuItem

@Composable
fun MainView() {
    MainContent()
}

@Composable
private fun MainContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //TODO Sumin - вынести цвета в тему
            .background(Brush.linearGradient(colors = listOf(Color(0xFF435159), Color(0xFF1F292E))))
    ) {
        var selectedItemIndex by remember { mutableStateOf(0) }
        Row(modifier = Modifier.fillMaxHeight()) {
            PVerticalMainMenu(
                items = listOf(
                    PVerticalMenuItem(Icons.Default.DirectionsCar, "General"),
                    PVerticalMenuItem(Icons.Default.Navigation, "Navigation"),
                    PVerticalMenuItem(Icons.Default.Apps, "Apps"),
                    PVerticalMenuItem(Icons.Default.BatteryChargingFull, "Charging"),
                ),
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
                selectedItemIndex = selectedItemIndex,
                onClickItem = { selectedItemIndex = it },
                isShowTitle = true
            )
        }
    }
}
