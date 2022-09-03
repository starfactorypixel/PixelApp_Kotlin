package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.starfactory.core.compose.Configuration
import ru.starfactory.core.compose.LocalConfiguration

@Composable
internal fun DashboardView(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    DashboardContent(state)
}

@Composable
private fun DashboardContent(
    state: DashboardViewState,
) {
    return when (state) {
        DashboardViewState.Loading -> Unit // Loading is very fast
        is DashboardViewState.ShowData -> ShowDataContent(state)
    }
}

@Composable
private fun ShowDataContent(
    state: DashboardViewState.ShowData,
) {
    val configuration = LocalConfiguration.current
    val screenSize = configuration.screenSize
    val orientation = configuration.orientation

    when {
        screenSize == Configuration.ScreenSize.Phone && orientation == Configuration.Orientation.PORTRAIT -> {
            DashboardPhonePortraitContent(state)
        }
        screenSize == Configuration.ScreenSize.Phone && orientation == Configuration.Orientation.LANDSCAPE -> {
            DashboardPhoneLandscapeContent(state)
        }
        else -> {
            DashboardTabletContent(state)
        }
    }
}

