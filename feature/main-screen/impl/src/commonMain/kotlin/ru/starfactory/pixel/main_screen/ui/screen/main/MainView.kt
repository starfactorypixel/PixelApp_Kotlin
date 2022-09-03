package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.compose.Configuration
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.ScreenInstance

@Composable
internal fun MainView(viewModel: MainViewModel, childStack: Value<ChildStack<Screen, ScreenInstance>>) {

    val state by viewModel.state.collectAsState()

    MainContent(
        state,
        viewModel::onSelectMenuItem,
        viewModel::onClickSettings,
        childStack
    )
}

@Composable
private fun MainContent(
    state: MainViewState,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    onClickSettings: () -> Unit,
    childStack: Value<ChildStack<Screen, ScreenInstance>>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paddingSystemWindowInsets()
    ) {
        val configuration = LocalConfiguration.current
        val screenSize = configuration.screenSize
        val orientation = configuration.orientation

        when {
            screenSize == Configuration.ScreenSize.Phone && orientation == Configuration.Orientation.PORTRAIT -> {
                MainContentPhonePortrait(state, onSelectMenuItem, onClickSettings, childStack)
            }
            screenSize == Configuration.ScreenSize.Phone && orientation == Configuration.Orientation.LANDSCAPE -> {
                MainContentPhoneLandscape(state, onSelectMenuItem, onClickSettings, childStack)
            }
            else -> {
                MainContentTablet(state, onSelectMenuItem, onClickSettings, childStack)
            }
        }
    }
}
