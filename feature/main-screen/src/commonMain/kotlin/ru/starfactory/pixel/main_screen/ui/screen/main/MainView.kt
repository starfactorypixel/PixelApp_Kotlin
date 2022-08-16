package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.receiveAsFlow
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.decompose.LocalComponentContext
import ru.starfactory.core.decompose.view_model.decomposeViewModel
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.*
import ru.starfactory.pixel.main_screen.ui.screen.GeneralScreen
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMainMenu
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMenuItem

@Composable
fun MainView() {
    val viewModel = decomposeViewModel<MainViewModel>()

    val componentContext = LocalComponentContext.current
    val navigation = remember { StackNavigation<Screen>() }
    val childStack = componentContext.defaultChildStack(navigation, GeneralScreen, "main-screen-router")

    LaunchedEffect(viewModel, navigation) {
        viewModel.setCurrentScreen.receiveAsFlow().collect { newScreen -> navigation.navigate { listOf(newScreen) } }
    }

    val state by viewModel.state.collectAsState()

    LocalNavigationHolder(navigation) {
        MainContent(state, viewModel::onSelectMenuItem, childStack)
    }
}

@Composable
private fun MainContent(
    state: MainViewState,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    childStack: Value<ChildStack<Screen, ScreenInstance>>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //TODO Sumin - вынести цвета в тему
            .background(Brush.linearGradient(colors = listOf(Color(0xFF435159), Color(0xFF1F292E))))
            .paddingSystemWindowInsets()
    ) {
        NavigationContentView(childStack)
        MainMenuContent(state.menuItems, onSelectMenuItem)
    }
}

@Composable
private fun MainMenuContent(
    items: List<MainViewState.MenuItem>,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    Row(modifier = Modifier.fillMaxHeight()) {
        PVerticalMainMenu(
            items = items.toPMenuItem(),
            Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            selectedItemIndex = selectedItemIndex,
            onClickItem = {
                selectedItemIndex = it
                onSelectMenuItem(items[it])
            },
            isShowTitle = true
        )
    }
}

private fun List<MainViewState.MenuItem>.toPMenuItem(): List<PVerticalMenuItem> = this.map { it.toPMenuItem() }

private fun MainViewState.MenuItem.toPMenuItem(): PVerticalMenuItem = when (this) {
    MainViewState.MenuItem.GENERAL -> PVerticalMenuItem(Icons.Default.DirectionsCar, "General")
    MainViewState.MenuItem.NAVIGATION -> PVerticalMenuItem(Icons.Default.Navigation, "Navigation")
    MainViewState.MenuItem.APPS -> PVerticalMenuItem(Icons.Default.Apps, "Apps")
    MainViewState.MenuItem.CHARGING -> PVerticalMenuItem(Icons.Default.BatteryChargingFull, "Charging")
}
