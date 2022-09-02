package ru.starfactory.core.uikit.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PArrowBottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    onArrowClick: (() -> Unit)? = null,
    arrow: @Composable ColumnScope.(progress: Float) -> Unit = { progress ->
        Icon(
            Icons.Default.KeyboardArrowUp, null,
            Modifier
                .clip(RoundedCornerShape(percent = 50))
                .run { if (onArrowClick != null) clickable { onArrowClick() } else this }
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
                .rotate(progress * 180f)
        )
    },
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    topBar: (@Composable () -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: (@Composable () -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    sheetGesturesEnabled: Boolean = true,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetElevation: Dp = 0.dp,
    sheetBackgroundColor: @Composable (progress: Float) -> Color = { PixelTheme.colors.surface.copy(alpha = it) },
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor(1f)),
    sheetPeekHeight: Dp = 40.dp, // 8 + 24 + 8
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.primary,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color = DrawerDefaults.scrimColor,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit
) {
    val progress = scaffoldState.currentFraction
    BottomSheetScaffold(
        sheetContent = {
            arrow(progress)
            sheetContent()
        },
        modifier,
        scaffoldState,
        topBar,
        snackbarHost,
        floatingActionButton,
        floatingActionButtonPosition,
        sheetGesturesEnabled,
        sheetShape,
        sheetElevation,
        sheetBackgroundColor(progress),
        sheetContentColor,
        sheetPeekHeight,
        drawerContent,
        drawerGesturesEnabled,
        drawerShape,
        drawerElevation,
        drawerBackgroundColor,
        drawerContentColor,
        drawerScrimColor,
        backgroundColor,
        contentColor,
        content
    )
}


@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val currentValue = bottomSheetState.currentValue
        val targetValue = bottomSheetState.targetValue

        return when {
            currentValue == BottomSheetValue.Collapsed && fraction != 1f -> fraction
            currentValue == BottomSheetValue.Expanded && fraction != 1f -> 1 - fraction

            targetValue == BottomSheetValue.Collapsed -> 0f
            targetValue == BottomSheetValue.Expanded -> 1f

            else -> throw IllegalStateException()
        }
    }