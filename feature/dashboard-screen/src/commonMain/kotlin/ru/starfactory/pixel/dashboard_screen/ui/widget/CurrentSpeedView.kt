package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private const val SPEED_ANIMATION_DURATION = 700

@Composable
internal fun CurrentSpeedView(
    speed: Int,
    modifier: Modifier = Modifier,
) {

    val animatedSpeed by animateIntAsState(speed, tween(SPEED_ANIMATION_DURATION))

    Column(modifier) {
        Text(
            "Speed",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.W600,
        )
        Row {
            Box(
                Modifier
                    .alignByBaseline()
                    .width(IntrinsicSize.Max)
            ) {
                // TODO Sumin: try to fix this
                // This little trick need to calculate text width with at least two digits
                // I didn't find another way to solve this problem
                Text(
                    "00",
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.W600,
                    color = Color.Transparent,
                )
                Text(
                    animatedSpeed.toString(),
                    Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.End
                )
            }
            Text(
                "km/h",
                Modifier.alignByBaseline().padding(start = 8.dp),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.W600,
            )
        }
    }
}
