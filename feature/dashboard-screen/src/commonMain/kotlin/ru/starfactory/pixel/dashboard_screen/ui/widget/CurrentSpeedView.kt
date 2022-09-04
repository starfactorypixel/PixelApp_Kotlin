package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun CurrentSpeedView(
    speed: Int,
    modifier: Modifier = Modifier,
) {
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
                    speed.toString(),
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
