package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.pixel.dashboard_screen.ui.dashboardiconpack.DashboardCar
import ru.starfactory.pixel.dashboard_screen.ui.widget.BottomActionsView
import ru.starfactory.pixel.dashboard_screen.ui.widget.CurrentSpeedView
import ru.starfactory.pixel.dashboard_screen.ui.widget.FastActionsView
import ru.starfactory.pixel.dashboard_screen.ui.widget.StatisticsView
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun DashboardView() {
    val viewModel: DashboardViewModel = decomposeViewModel()
    val state by viewModel.state.collectAsState()
    DashboardContent(state)
}

@Composable
private fun DashboardContent(state: DashboardViewState) {
    return when (state) {
        DashboardViewState.Loading -> Unit // Loading is very fast
        is DashboardViewState.ShowData -> ShowDataContent(state)
    }
}

@Composable
private fun ShowDataContent(state: DashboardViewState.ShowData) {
    val mainMenuInsets = LocalMainMenuInsets.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        Row {
            CurrentSpeedView(
                state.primaryState.speed,
                Modifier.padding(horizontal = 32.dp)
            )
            Spacer(Modifier.weight(1f))
            StatisticsView(
                batteryCharge = state.primaryState.batteryCharge.toInt(),
                Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.weight(1f))
            FastActionsView(Modifier.padding(horizontal = 16.dp))
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
                .weight(1f)
        ) {
            if (mainMenuInsets.isPositioned) {
                Icon(
                    Icons.DashboardCar,
                    null,
                    Modifier.align(Alignment.Center)
                )
            }
        }
        BottomActionsView(Modifier.fillMaxWidth())
    }
}
