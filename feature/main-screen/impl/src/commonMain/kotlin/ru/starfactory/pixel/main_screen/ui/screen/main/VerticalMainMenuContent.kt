package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets
import ru.starfactory.pixel.main_screen.ui.widged.PVerticalMainMenu

@Composable
internal fun VerticalMainMenuContent(
    items: List<MainViewState.MenuItem>,
    selectedItem: MainViewState.MenuItem,
    isShowTitle: Boolean,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    mainMenuInsets: MutableState<MainMenuInsets>,
) {
    val localDensity = LocalDensity.current

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