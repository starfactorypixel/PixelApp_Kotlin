package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsetsHolder
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets

@Composable
internal fun MainContentPhoneLandscape(
    state: MainViewState,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    onClickSettings: () -> Unit,
    childStack: Value<ChildStack<Screen, ScreenInstance>>
) {
    val mainMenuInsets = remember { mutableStateOf(MainMenuInsets()) }
    LocalMainMenuInsetsHolder(mainMenuInsets.value) {
        Column(Modifier.fillMaxSize()) {
            CollapsingActionsMainMenuContent(Modifier.weight(1f), onClickSettings) {
                Box {
                    VerticalMainMenuContent(
                        state.menuItems,
                        state.selectedMenuItem,
                        false,
                        onSelectMenuItem,
                        mainMenuInsets,
                    )
                    // it is okay doesn't apply padding here
                    NavigationContentView(childStack, Modifier.fillMaxSize())
                }
            }
        }
    }
}
