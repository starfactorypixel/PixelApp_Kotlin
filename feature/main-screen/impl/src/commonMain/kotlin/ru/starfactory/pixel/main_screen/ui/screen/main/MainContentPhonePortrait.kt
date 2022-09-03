package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
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
                Column(Modifier.padding(it)) {
                    NavigationContentView(childStack, Modifier.weight(1f))
                }
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
