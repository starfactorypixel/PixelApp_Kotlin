package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import android.content.res.Configuration as ConfigurationPlatform

@Composable
actual fun LocalConfigurationHolder(content: @Composable () -> Unit) {
    val androidConfiguration = androidx.compose.ui.platform.LocalConfiguration.current

    val configuration = Configuration(
        screenWidth = androidConfiguration.screenWidthDp.dp,
        screenHeight = androidConfiguration.screenHeightDp.dp,
        orientation = when (val value = androidConfiguration.orientation) {
            ConfigurationPlatform.ORIENTATION_LANDSCAPE -> Configuration.Orientation.LANDSCAPE
            ConfigurationPlatform.ORIENTATION_PORTRAIT -> Configuration.Orientation.PORTRAIT
            ConfigurationPlatform.ORIENTATION_UNDEFINED -> Configuration.Orientation.PORTRAIT // i'm think this is ok
            else -> error("Unexpected orientation = $value")
        }
    )

    CompositionLocalProvider(LocalConfiguration provides configuration, content = content)
}
