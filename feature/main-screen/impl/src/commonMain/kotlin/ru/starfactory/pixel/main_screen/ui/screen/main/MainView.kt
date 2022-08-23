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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.*
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsetsHolder
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMainMenu
import ru.starfactory.pixel.main_screen.ui.widged.main_menu.PVerticalMenuItem

@Composable
internal fun MainView(viewModel: MainViewModel) {

    val state by viewModel.state.collectAsState()

    MainContent(state, viewModel::onSelectMenuItem, viewModel.childStack)
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
        var mainMenuInsets by remember { mutableStateOf(MainMenuInsets()) }
        val localDensity = LocalDensity.current

        LocalMainMenuInsetsHolder(mainMenuInsets) {
            NavigationContentView(childStack)
        }

        Row(modifier = Modifier.fillMaxHeight()) {
            MainMenuContent(
                state.menuItems,
                onSelectMenuItem,
                Modifier
                    .onGloballyPositioned { coordinates ->
                        with(localDensity) {
                            val offset = coordinates.positionInRoot()
                            val size = coordinates.size
                            mainMenuInsets = MainMenuInsets(
                                DpOffset(offset.x.toDp(), offset.y.toDp()),
                                DpSize(size.width.toDp(), size.height.toDp()),
                                isPositioned = true
                            )
                        }
                    }
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun MainMenuContent(
    items: List<MainViewState.MenuItem>,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isShowTitle = LocalConfiguration.current.screenWidth > 600.dp
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    PVerticalMainMenu(
        items = items.toPMenuItem(),
        modifier,
        selectedItemIndex = selectedItemIndex,
        onClickItem = {
            selectedItemIndex = it
            onSelectMenuItem(items[it])
        },
        isShowTitle = isShowTitle
    )
}

private fun List<MainViewState.MenuItem>.toPMenuItem(): List<PVerticalMenuItem> = this.map { it.toPMenuItem() }

private fun MainViewState.MenuItem.toPMenuItem(): PVerticalMenuItem = when (this) {
    MainViewState.MenuItem.GENERAL -> PVerticalMenuItem(Icons.Default.DirectionsCar, "General")
    MainViewState.MenuItem.NAVIGATION -> PVerticalMenuItem(Icons.Default.Navigation, "Navigation")
    MainViewState.MenuItem.APPS -> PVerticalMenuItem(Icons.Default.Apps, "Apps")
    MainViewState.MenuItem.CHARGING -> PVerticalMenuItem(Icons.Default.BatteryChargingFull, "Charging")
}
