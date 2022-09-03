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

@Composable
internal fun DashboardPhonePortraitContent(
    state: DashboardViewState.ShowData,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        CurrentSpeedView(
            state.primaryState.speed,
        )
        StatisticsView(
            batteryCharge = state.primaryState.batteryCharge.toInt(),
            Modifier.padding(top = 16.dp)
        )
        FastActionsView(
            Modifier
                .padding(top = 16.dp)
        )
    }
}


