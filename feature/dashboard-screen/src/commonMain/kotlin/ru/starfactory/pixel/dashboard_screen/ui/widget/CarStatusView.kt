package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import ru.starfactory.core.uikit.view.POutlinedFloatingActionButton
import ru.starfactory.pixel.dashboard_screen.ui.dashboardiconpack.DashboardCar

@Composable
fun CarStatusView(
    indicators: List<CarStatusIndicator>,
    modifier: Modifier = Modifier
) {

    val groupedIndicators = indicators.groupBy {
        it.position
    }


    val carIcon = Icons.DashboardCar
    val carAspect = carIcon.viewportWidth / carIcon.viewportHeight


    val carIconContent = @Composable {
        Image(
            carIcon,
            null,
            Modifier
                .background(Color.Green.copy(alpha = .2f)),
        )
    }

    val canvas = @Composable {
        Canvas(Modifier) {

        }
    }

    val content = @Composable {
        carIconContent()
        canvas()

        groupedIndicators[IndicatorPosition.START]?.forEach { indicator ->
            CarStatusIndicatorContent(indicator.icon)
        }
        groupedIndicators[IndicatorPosition.CENTER]?.forEach { indicator ->
            CarStatusIndicatorContent(indicator.icon)
        }
        groupedIndicators[IndicatorPosition.END]?.forEach { indicator ->
            CarStatusIndicatorContent(indicator.icon)
        }

        Unit
    }

    Layout(content, modifier) { measurables: List<Measurable>, constraints: Constraints ->
        // Step 0: get all measurables
        var position = 0
        val carMeasurable = measurables[position++]
        val canvasMeasurable = measurables[position++]
        val startIndicatorsMeasurables = measurables.subList(position, position + (groupedIndicators[IndicatorPosition.START]?.size ?: 0))
        position += startIndicatorsMeasurables.size
        val centerIndicatorsMeasurables = measurables.subList(position, position + (groupedIndicators[IndicatorPosition.CENTER]?.size ?: 0))
        position += centerIndicatorsMeasurables.size
        val endIndicatorsMeasurables = measurables.subList(position, position + (groupedIndicators[IndicatorPosition.END]?.size ?: 0))
        position += endIndicatorsMeasurables.size
        // ****************************

        val zeroMinSizeConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val containerSize = IntSize(constraints.maxWidth, constraints.maxHeight)


        // Step 1: measure START indicators
        val startIndicatorsPlaceable = startIndicatorsMeasurables.map { it.measure(zeroMinSizeConstraints) }
        val startIndicatorsWidth = startIndicatorsPlaceable.maxOfOrNull { it.measuredWidth } ?: 0

        // Step 2: measure END indicators
        val endIndicatorsPlaceable = endIndicatorsMeasurables.map { it.measure(zeroMinSizeConstraints) }
        val endIndicatorsWidth = startIndicatorsPlaceable.maxOfOrNull { it.measuredWidth } ?: 0

        // Step 3: measure car
        val carFrame = IntSize(containerSize.width - startIndicatorsWidth - endIndicatorsWidth, containerSize.height)
        val carFrameAspect = carFrame.width.toFloat() / carFrame.height
        val carConstraints = if (carAspect > carFrameAspect) {
            Constraints.fixed(width = carFrame.width, height = (carFrame.width / carAspect).toInt())
        } else {
            Constraints.fixed(width = (carFrame.height * carAspect).toInt(), height = carFrame.height)
        }
        val carPlaceable = carMeasurable.measure(carConstraints)


        //val canvasPlaceable = canvasMeasurable.measure(constraints)


        layout(containerSize.width, containerSize.height) {
            carPlaceable.place((containerSize.width - carPlaceable.width) / 2, (containerSize.height - carPlaceable.height) / 2)

            //  canvasPlaceable.place(0, 0)


        }
    }

//    Box(
//        modifier
//    ) {
//        CarStatusIcon()
//        Icon(
//            Icons.DashboardCar,
//            null,
//            Modifier
//                .fillMaxSize()
//                .align(Alignment.Center)
//        )
//    }
}

data class CarStatusIndicator(
    val x: Float, // %
    val y: Float, // %
    val icon: ImageVector,
)

private val CarStatusIndicator.position: IndicatorPosition
    get() = when (x) {
        in 0f..0.45f -> IndicatorPosition.START
        in 0.45f..0.55f -> IndicatorPosition.CENTER
        in 0.55f..1f -> IndicatorPosition.END
        else -> throw IllegalStateException("x coordinate must be in range 0..1, x=$x")
    }

@Composable
private fun CarStatusIndicatorContent(icon: ImageVector) {
    POutlinedFloatingActionButton(onClick = {}) {
        Icon(icon, null)
    }
}

private enum class IndicatorPosition {
    START, CENTER, END
}