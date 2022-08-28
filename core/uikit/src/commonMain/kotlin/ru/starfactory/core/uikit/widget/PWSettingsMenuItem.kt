package ru.starfactory.core.uikit.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.view.POutlinedCard

@Composable
fun PWSettingsMenuItem(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    color: Color? = null,
) {
    POutlinedCard(
        modifier
            .defaultMinSize(minWidth = 180.dp, minHeight = 100.dp)
            .clickable(onClick = onClick),
        backgroundColor = color?.copy(alpha = .15f) ?: PixelTheme.colors.surface,
        border = BorderStroke(1.dp, color ?: Color.White)
    ) {
        Box {
            Row(Modifier.align(Alignment.Center)) {
                Icon(icon, null)
                Text(text, Modifier.padding(start = 8.dp))
            }
        }
    }
}