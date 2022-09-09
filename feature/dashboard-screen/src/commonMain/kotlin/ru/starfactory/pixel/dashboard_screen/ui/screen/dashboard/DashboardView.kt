package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.Configuration
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.view.POutlinedButton
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.WithLocalMainMenuInsets

@Composable
internal fun DashboardView(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    DashboardContent(state, viewModel::onClickSelectSource)
}

@Composable
private fun DashboardContent(
    state: DashboardViewState,
    onClickSelectSource: () -> Unit,
) {
    return when (state) {
        DashboardViewState.Loading -> Unit // Loading is very fast
        DashboardViewState.SourceNotSelected -> SourceNotSelectedContent(onClickSelectSource)
        is DashboardViewState.ShowData -> DataContent(state)
    }
}

@Composable
private fun SourceNotSelectedContent(onClickSelectSource: () -> Unit) {
    WithLocalMainMenuInsets { mainMenuInsets ->
        Box(
            Modifier
                .padding(start = mainMenuInsets.positionInRoot.x + mainMenuInsets.size.width)
                .fillMaxSize()
        ) {
            Column(Modifier.align(Alignment.Center)) {
                Text(
                    "Source not selected or not existed",
                    Modifier.align(Alignment.CenterHorizontally),
                    style = PixelTheme.typography.h6
                )
                POutlinedButton(
                    onClick = onClickSelectSource,
                    Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Select source")
                }
            }
        }
    }
}

@Composable
private fun DataContent(
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
