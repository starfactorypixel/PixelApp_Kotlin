package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.pixel.dashboard_screen.ui.widget.*
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.WithLocalMainMenuInsets

@Composable
internal fun DashboardPhoneLandscapeContent(
    state: DashboardViewState.ShowData,
) {
    WithLocalMainMenuInsets { mainMenuInsets ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
                .padding(vertical = 32.dp)
        ) {
            CurrentSpeedView(
                state.primaryState.speed,
            )
            StatisticsView(
                batteryCharge = state.primaryState.batteryCharge.toInt(),
                Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

