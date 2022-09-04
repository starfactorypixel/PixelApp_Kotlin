package ru.starfactory.pixel.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.starfactory.pixel.ui.view.ArcProgressBar
import ru.starfactory.pixel.ui.view.AutoSizeText
import kotlin.math.min

@OptIn(ExperimentalTextApi::class)
@Composable
fun ArcValueIndicatorWidget(
    modifier: Modifier = Modifier.fillMaxSize(),
    currentValue: Int,
    maxValue: Int,
    text: String,
) {
    val progress by animateFloatAsState(
        targetValue = min(currentValue, maxValue) / maxValue.toFloat(),
        animationSpec = tween(300)
    )

    ArcProgressBar(
        modifier,
        progress = progress,
        strokeWidth = 18.dp,
        frameWidth = 24.dp,
    ) {
        Column(Modifier.fillMaxSize()) {
            AutoSizeText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f), // TODO нужно правильное измерение
                text = currentValue.toString(),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 400.sp,
                    fontWeight = FontWeight.Bold,
                    platformStyle = PlatformTextStyle(false),
                ),
            )
            Text(
                text = text,
                Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h6
            )
        }
    }
}
