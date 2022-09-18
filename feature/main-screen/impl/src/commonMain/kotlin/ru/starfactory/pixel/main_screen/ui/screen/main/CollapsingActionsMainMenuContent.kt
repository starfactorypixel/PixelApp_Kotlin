package ru.starfactory.pixel.main_screen.ui.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.starfactory.core.uikit.view.PArrowBottomSheetScaffold
import ru.starfactory.pixel.main_screen.ui.widged.BottomActionsView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollapsingActionsMainMenuContent(
    modifier: Modifier = Modifier,
    onClickSettings: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    PArrowBottomSheetScaffold(
        sheetContent = {
            BottomActionsView(
                Modifier
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                onClickSettings = onClickSettings,
            )
        },
        modifier,
        onArrowClick = {
            coroutineScope.launch {
                if (scaffoldState.bottomSheetState.isExpanded) scaffoldState.bottomSheetState.collapse()
                else scaffoldState.bottomSheetState.expand()
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = Color.Transparent,
        content = content
    )
}
