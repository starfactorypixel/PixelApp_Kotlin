package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.starfactory.core.navigation.Screen
import ru.starfactory.core.navigation.ui.NavigationContentView
import ru.starfactory.core.navigation.ui.ScreenInstance
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.LocalMainMenuInsetsHolder
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets
import ru.starfactory.pixel.main_screen.ui.widged.BottomActionsView

@Composable
internal fun MainContentTablet(
    state: MainViewState,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    onClickSettings: () -> Unit,
    childStack: Value<ChildStack<Screen, ScreenInstance>>
) {
    val mainMenuInsets = remember { mutableStateOf(MainMenuInsets()) }

    VerticalMainMenuContent(
        state.menuItems,
        state.selectedMenuItem,
        true,
        onSelectMenuItem,
        mainMenuInsets,
    )

    LocalMainMenuInsetsHolder(mainMenuInsets.value) {
        Column {
            NavigationContentView(childStack, Modifier.weight(1f))
            BottomActionsView(
                Modifier
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                onClickSettings = onClickSettings,
            )
        }
    }
}
