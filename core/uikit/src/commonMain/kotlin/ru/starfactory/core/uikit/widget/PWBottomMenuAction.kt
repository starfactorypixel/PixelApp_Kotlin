package ru.starfactory.core.uikit.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.reverse
import ru.starfactory.core.uikit.theme.DEFAULT_CONTENT_ALPHA
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.view.POutlinedFloatingActionButton

@Composable
fun PWBottomMenuAction(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    borderColor: Color = PixelTheme.colors.border,
    orientation: PWBottomMenuActionOrientation = PWBottomMenuActionOrientation.Vertical,
    onClick: () -> Unit = {},
) {

    Container(
        orientation,
        modifier
            .clip(PixelTheme.shapes.medium)
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        POutlinedFloatingActionButton(
            onClick = onClick,
            backgroundColor = borderColor.copy(DEFAULT_CONTENT_ALPHA),
            borderStroke = BorderStroke(2.dp, borderColor),
        ) {
            Icon(icon, null, Modifier.size(32.dp))
        }

        Text(
            text,
            Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun Container(orientation: PWBottomMenuActionOrientation, modifier: Modifier, content: @Composable () -> Unit) {
    when (orientation) {
        PWBottomMenuActionOrientation.Vertical -> Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) { content() }
        PWBottomMenuActionOrientation.HorizontalStart -> Row(modifier, verticalAlignment = Alignment.CenterVertically) { content() }
        PWBottomMenuActionOrientation.HorizontalEnd -> {
            val reversedDirection = LocalLayoutDirection.current.reverse()
            CompositionLocalProvider(LocalLayoutDirection provides reversedDirection) {
                Row(modifier, verticalAlignment = Alignment.CenterVertically) { content() }
            }
        }
    }
}

enum class PWBottomMenuActionOrientation {
    Vertical, HorizontalStart, HorizontalEnd
}
