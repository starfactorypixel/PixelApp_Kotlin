package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsetsHolder
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets

@Composable
internal fun MainContentPhonePortrait(
    state: MainViewState,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    onClickSettings: () -> Unit,
    childStack: Value<ChildStack<Screen, ScreenInstance>>
) {
    val mainMenuInsets = remember { mutableStateOf(MainMenuInsets()) }

    LocalMainMenuInsetsHolder(mainMenuInsets.value) {
        Column {
            CollapsingActionsMainMenuContent(Modifier.weight(1f), onClickSettings) {
                // it is okay doesn't apply padding here
                NavigationContentView(childStack, Modifier.fillMaxSize())
            }

            HorizontalMainMenuContent(
                state.menuItems,
                state.selectedMenuItem,
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
