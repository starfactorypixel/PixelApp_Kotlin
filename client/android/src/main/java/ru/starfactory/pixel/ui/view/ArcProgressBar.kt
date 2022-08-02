package ru.starfactory.pixel.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.ui.theme.PixelTheme

@Composable
fun ArcProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    strokeWidth: Dp = 6.dp,
    frameWidth: Dp = 12.dp,
    strokeColor: Color = MaterialTheme.colors.primary,
    frameColor: Color = MaterialTheme.colors.onPrimary,
    content: @Composable BoxScope .() -> Unit = {}
) {

    var rootWidth by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .onGloballyPositioned { layoutCoordinates ->
                rootWidth = layoutCoordinates.size.width
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val offset = Offset(frameWidth.toPx() / 2, frameWidth.toPx() / 2)
            val size = Size(this.size.width - 2 * offset.x, this.size.height - 2 * offset.y)
            // frame
            drawArc(
                color = frameColor,
                startAngle = 180f - 30f,
                sweepAngle = 180f + 60f,
                useCenter = false,
                style = Stroke(frameWidth.toPx(), cap = StrokeCap.Round),
                topLeft = offset,
                size = size
            )

            // progress
            drawArc(
                color = strokeColor,
                startAngle = 180f - 30f,
                sweepAngle = (180f + 60f) * progress,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = offset,
                size = size
            )
        }

        // TODO учитывать frameWidth
        val padding = with(LocalDensity.current) { rootWidth.toDp() } / 10

        // Internal content
        Box(
            Modifier
                .align(Alignment.Center)
                .aspectRatio(4f / 3f)
                .padding(padding),
            content = content
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Preview(showBackground = true)
@Composable
fun ArcProgressBarPreview() {
    PixelTheme(darkTheme = true) {
        ArcProgressBar(
            modifier = Modifier
                .size(300.dp)
                .background(MaterialTheme.colors.background),
            progress = 0.63f
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = .2f))
            )
        }
    }
}
