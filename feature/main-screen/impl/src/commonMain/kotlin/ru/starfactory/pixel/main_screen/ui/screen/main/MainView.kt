package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.compose.Configuration
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.core.compose.paddingSystemWindowInsets
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.*
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsetsHolder
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets
import ru.starfactory.pixel.main_screen.ui.widged.BottomActionsView
import ru.starfactory.pixel.main_screen.ui.widged.PVerticalMainMenu
import ru.starfactory.pixel.main_screen.ui.widged.PVerticalMenuItem

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
        val mainMenuInsets = remember { mutableStateOf(MainMenuInsets()) }
        val menuType = MenuType.fromConfiguration(LocalConfiguration.current)

        LocalMainMenuInsetsHolder(mainMenuInsets.value) {
            Column {
                NavigationContentView(childStack, Modifier.weight(1f))
                BottomActionsView(
                    Modifier
                        .padding(bottom = 16.dp)
                        .padding(horizontal = 16.dp),
                    onClickSettings = onClickSettings,
                )
                if (menuType == MenuType.Bottom) {
                    HorizontalMainMenuContent(
                        state.menuItems,
                        state.selectedMenuItem,
                        onSelectMenuItem,
                        mainMenuInsets,
                    )
                }
            }
        }

        if (menuType >= MenuType.Start) {
            VerticalMainMenuContent(
                state.menuItems,
                state.selectedMenuItem,
                menuType,
                onSelectMenuItem,
                mainMenuInsets,
            )
        }
    }
}

@Composable
private fun HorizontalMainMenuContent(
    items: List<MainViewState.MenuItem>,
    selectedItem: MainViewState.MenuItem,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    mainMenuInsets: MutableState<MainMenuInsets>,
) {
    LaunchedEffect(Unit) { mainMenuInsets.value = MainMenuInsets(isPositioned = true) }
    Column {
        Divider(Modifier.fillMaxWidth())
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        ) {
            items.forEach {
                val item = it.toPMenuItem()
                BottomNavigationItem(
                    selected = item.id == selectedItem,
                    onClick = { onSelectMenuItem(item.id) },
                    icon = { Icon(item.icon, null) }
                )
            }
        }
    }
}

@Composable
private fun VerticalMainMenuContent(
    items: List<MainViewState.MenuItem>,
    selectedItem: MainViewState.MenuItem,
    menuType: MenuType,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    mainMenuInsets: MutableState<MainMenuInsets>,
) {
    val localDensity = LocalDensity.current
    val isShowTitle = menuType == MenuType.StartExpanded

    Row(modifier = Modifier.fillMaxHeight()) {
        PVerticalMainMenu(
            items = items.toPMenuItem(),
            Modifier
                .onGloballyPositioned { coordinates ->
                    with(localDensity) {
                        val offset = coordinates.positionInRoot()
                        val size = coordinates.size
                        mainMenuInsets.value = MainMenuInsets(
                            DpOffset(offset.x.toDp(), offset.y.toDp()),
                            DpSize(size.width.toDp(), size.height.toDp()),
                            isPositioned = true
                        )
                    }
                }
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            selectedItemId = selectedItem,
            onClickItem = onSelectMenuItem,
            isShowTitle = isShowTitle
        )
    }
}

private enum class MenuType {
    Bottom,
    Start,
    StartExpanded;

    companion object {
        fun fromConfiguration(configuration: Configuration) = when (configuration.screenWidth) {
            in 0.dp..399.dp -> Bottom
            in 400.dp..599.dp -> Start
            else -> StartExpanded
        }
    }
}

private fun List<MainViewState.MenuItem>.toPMenuItem(): List<PVerticalMenuItem<MainViewState.MenuItem>> = this.map { it.toPMenuItem() }

private fun MainViewState.MenuItem.toPMenuItem(): PVerticalMenuItem<MainViewState.MenuItem> = when (this) {
    MainViewState.MenuItem.GENERAL -> PVerticalMenuItem(id = this, Icons.Default.DirectionsCar, "General")
    MainViewState.MenuItem.NAVIGATION -> PVerticalMenuItem(id = this, Icons.Default.Navigation, "Navigation")
    MainViewState.MenuItem.APPS -> PVerticalMenuItem(id = this, Icons.Default.Apps, "Apps")
    MainViewState.MenuItem.CHARGING -> PVerticalMenuItem(id = this, Icons.Default.BatteryChargingFull, "Charging")
}
