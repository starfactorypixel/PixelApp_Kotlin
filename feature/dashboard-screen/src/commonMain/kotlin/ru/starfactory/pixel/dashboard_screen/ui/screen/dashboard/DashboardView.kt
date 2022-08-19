package ru.starfactory.pixel.dashboard_screen.ui.screen.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import ru.starfactory.pixel.dashboard_screen.ui.widget.CurrentSpeedView
import ru.starfactory.pixel.dashboard_screen.ui.widget.FastActionsView
import ru.starfactory.pixel.dashboard_screen.ui.widget.StatisticsView

@Composable
internal fun DashboardView() {
    DashboardContent()
}

@Composable
private fun DashboardContent() {
    val transition = rememberInfiniteTransition()
    val speed by transition.animateFloat(
        initialValue = 0f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        Row {
            CurrentSpeedView(
                speed.toInt(),
                Modifier.padding(horizontal = 32.dp)
            )
            Spacer(Modifier.weight(1f))
            StatisticsView(Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.weight(1f))
            FastActionsView(Modifier.padding(horizontal = 16.dp))
        }
    }
}
