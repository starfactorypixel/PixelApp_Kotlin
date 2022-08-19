package ru.starfactory.core.uikit.view.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun POutlinedSwitch(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {

    val borderColor by animateColorAsState(
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    OutlinedButton(
        onClick = onClick,
        modifier
            .defaultMinSize(minHeight = 38.dp),
        shape = RoundedCornerShape(percent = 50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.White.copy(alpha = 0.04f),
            contentColor = MaterialTheme.colors.onSurface,
        ),
        border = BorderStroke(1.dp, borderColor),
        content = content
    )
}

