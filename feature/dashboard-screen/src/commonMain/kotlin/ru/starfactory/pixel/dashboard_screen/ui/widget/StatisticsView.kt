package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun StatisticsView(
    batteryCharge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            "Statistics",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.W600,
        )
        Spacer(Modifier.padding(top = 8.dp))
        StatisticsRow(Icons.Default.BatteryFull, "$batteryCharge %")
        StatisticsRow(Icons.Default.WbSunny, "340 w/h")
        StatisticsRow(Icons.Default.Speed, "128 001 km")
    }
}

@Composable
private fun StatisticsRow(icon: ImageVector, text: String) {
    Row(Modifier.padding(top = 8.dp)) {
        Icon(
            icon,
            null,
            Modifier.size(24.dp)
        )
        Text(
            text,
            Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.W600,
        )
    }
}