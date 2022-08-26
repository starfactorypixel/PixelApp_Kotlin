package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.pixel.dashboard_screen.ui.widget.*
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsets

@Composable
internal fun DashboardView(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    DashboardContent(state, viewModel::onClickSettings)
}

@Composable
private fun DashboardContent(
    state: DashboardViewState,
    onClickSettings: () -> Unit,
) {
    return when (state) {
        DashboardViewState.Loading -> Unit // Loading is very fast
        is DashboardViewState.ShowData -> ShowDataContent(state, onClickSettings)
    }
}

@Composable
private fun ShowDataContent(
    state: DashboardViewState.ShowData,
    onClickSettings: () -> Unit
) {
    val mainMenuInsets = LocalMainMenuInsets.current
    val configuration = LocalConfiguration.current

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

            if (configuration.screenWidth > 600.dp) {
                FastActionsView(Modifier.padding(horizontal = 16.dp))
            }
        }
        if (mainMenuInsets.isPositioned) {
            CarStatusView(

                listOf(
                    CarStatusIndicator(
                        .15f, .5f, Icons.Default.Lock, "Door\nLocked", Color.Green
                    ),
                    CarStatusIndicator(
                        .5f, .20f, Icons.Default.Lock, "Text two\nline", Color.Yellow
                    ),
                    CarStatusIndicator(
                        .85f, .5f, Icons.Default.Lock, "Door\nUnlocked", Color.Red
                    ),
                ),


                Modifier
                    .fillMaxWidth()
                    .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
                    .padding(horizontal = 16.dp, vertical = 48.dp)
                    .weight(1f),
            )
        }
        BottomActionsView(onClickSettings, Modifier.fillMaxWidth())
    }
}

