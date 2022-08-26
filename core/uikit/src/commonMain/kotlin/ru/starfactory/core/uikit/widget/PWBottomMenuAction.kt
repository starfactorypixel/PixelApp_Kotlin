package ru.starfactory.core.uikit.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.view.POutlinedFloatingActionButton

@Composable
fun PWBottomMenuAction(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    borderColor: Color = PixelTheme.colors.surface,
    onClick: () -> Unit = {},
) {
    Column(modifier) {
        POutlinedFloatingActionButton(
            onClick = onClick,
            Modifier.align(Alignment.CenterHorizontally),
            //TODO Sumin вынести альфу в константы
            backgroundColor = borderColor.copy(.15f),
            borderStroke = BorderStroke(2.dp, borderColor),
        ) {
            Icon(icon, null, Modifier.size(32.dp))
        }

        Text(
            text,
            Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}