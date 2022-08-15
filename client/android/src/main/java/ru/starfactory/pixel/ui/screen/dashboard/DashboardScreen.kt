package ru.starfactory.pixel.ui.screen.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.pixel.ui.widget.ArcValueIndicatorWidget

@Composable
fun DashboardScreen() {
    DashboardContent()
}

@Composable
private fun DashboardContent() {
    Column(Modifier.fillMaxSize()) {
        Speed()
        Battery()
    }
}


@Composable
private fun ColumnScope.Battery() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Reverse
        )
    )


    ArcValueIndicatorWidget(
        Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
        currentValue = progress.toInt(),
        maxValue = 100,
        text = "%"
    )
}

@Composable
private fun ColumnScope.Speed() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Reverse
        )
    )


    ArcValueIndicatorWidget(
        Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
        currentValue = progress.toInt(),
        maxValue = 60,
        text = "km/h"
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    PixelTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DashboardContent()
        }
    }
}
