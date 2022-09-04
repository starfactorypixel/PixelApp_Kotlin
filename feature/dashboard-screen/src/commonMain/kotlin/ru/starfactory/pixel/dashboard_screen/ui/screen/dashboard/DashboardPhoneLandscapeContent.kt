package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.dashboard_screen.ui.widget.CurrentSpeedView
import ru.starfactory.pixel.dashboard_screen.ui.widget.StatisticsView
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
