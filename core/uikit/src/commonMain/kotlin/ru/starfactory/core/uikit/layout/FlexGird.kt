package ru.starfactory.core.uikit.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.lang.Integer.max
import kotlin.math.ceil
import kotlin.math.min

@Composable
fun PFlexVerticalGrid(
    minCount: Int,
    maxCount: Int,
    modifier: Modifier = Modifier,
    verticalSpacing: Dp = 16.dp,
    horizontalSpacing: Dp = 16.dp,
    content: @Composable () -> Unit
) {
    require(minCount > 0)
    require(maxCount > 0)

    Layout(content, modifier) { measurables: List<Measurable>, constraints: Constraints ->
        val verticalSpacingPx = verticalSpacing.toPx()
        val horizontalSpacingPx = horizontalSpacing.toPx()

        val childMaxWidth = max((constraints.maxWidth / minCount - horizontalSpacingPx * (minCount - 1)).toInt(), 0)

        val maxWidth = min(measurables.maxOf { it.maxIntrinsicWidth(constraints.maxHeight) }, childMaxWidth)
        val maxHeight = measurables.maxOf { it.maxIntrinsicHeight(maxWidth) }

        val childConstraints = Constraints.fixed(maxWidth, maxHeight)

        val placeables = measurables.map { it.measure(childConstraints) }

        var contentWidth = 0
        var countHorizontal = 0
        for (i in min(maxCount, placeables.size) downTo 1) {
            contentWidth = maxWidth * i + (verticalSpacingPx * (i - 1)).toInt()
            countHorizontal = i
            if (contentWidth <= constraints.maxWidth) break
        }

        val countVertical = ceil(measurables.size / countHorizontal.toFloat()).toInt()
        val contentHeight = (maxHeight * countVertical + verticalSpacingPx * (countVertical - 1)).toInt()

        layout(
            contentWidth,
            contentHeight
        ) {
            var position = 0
            var x = 0
            var y = 0
            placeables.forEach {
                it.place(x, y)

                position = (position + 1) % countHorizontal
                if (position == 0) {
                    x = 0
                    y += maxHeight + horizontalSpacingPx.toInt()
                } else {
                    x += maxWidth + verticalSpacingPx.toInt()
                }
            }
        }
    }
}
