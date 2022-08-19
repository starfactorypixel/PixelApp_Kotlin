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

@Composable
internal fun DashboardView() {
    DashboardContent()
}

@Composable
private fun DashboardContent() {
    Box(Modifier.fillMaxSize()) {
        val transition = rememberInfiniteTransition()
        val speed by transition.animateFloat(
            initialValue = 0f,
            targetValue = 40f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000),
                repeatMode = RepeatMode.Reverse
            )
        )

        CurrentSpeedView(
            speed.toInt(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
