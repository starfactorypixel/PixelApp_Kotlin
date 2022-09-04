package ru.starfactory.core.uikit.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun POutlinedCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = BorderStroke(1.dp, Color.White),
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Card(modifier, shape, backgroundColor, contentColor, border, elevation, content)
}
