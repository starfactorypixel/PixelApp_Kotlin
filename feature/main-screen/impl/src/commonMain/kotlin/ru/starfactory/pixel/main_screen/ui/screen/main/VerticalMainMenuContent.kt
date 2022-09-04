package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.systemBars
import ru.starfactory.pixel.main_screen.ui.main_menu_insets.MainMenuInsets
import ru.starfactory.pixel.main_screen.ui.widged.PVerticalMainMenu

@Composable
internal fun VerticalMainMenuContent(
    items: List<MainViewState.MenuItem>,
    selectedItem: MainViewState.MenuItem,
    isShowTitle: Boolean,
    onSelectMenuItem: (MainViewState.MenuItem) -> Unit,
    mainMenuInsets: MutableState<MainMenuInsets>,
    isSubtractWindowPaddings: Boolean = true,
) {
    val localDensity = LocalDensity.current
    val windowInsets = WindowInsets.systemBars.asPaddingValues()

    Row(modifier = Modifier.fillMaxHeight()) {
        PVerticalMainMenu(
            items = items.toPMenuItem(),
            Modifier
                .onGloballyPositioned { coordinates ->
                    with(localDensity) {
                        val offset = coordinates.positionInRoot()
                        val size = coordinates.size

                        val x = offset.x.toDp() - if (isSubtractWindowPaddings)
                            windowInsets.calculateLeftPadding(LayoutDirection.Ltr) else 0.dp
                        val y = offset.y.toDp() - if (isSubtractWindowPaddings) windowInsets.calculateTopPadding() else 0.dp

                        mainMenuInsets.value = MainMenuInsets(
                            DpOffset(x, y),
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
