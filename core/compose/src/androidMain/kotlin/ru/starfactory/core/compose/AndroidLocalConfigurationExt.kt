package ru.starfactory.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import android.content.res.Configuration as ConfigurationPlatform

@Composable
actual fun LocalConfigurationHolder(content: @Composable () -> Unit) {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    CompositionLocalProvider(LocalConfiguration provides AndroidConfiguration(configuration), content = content)
}

private class AndroidConfiguration(private val configuration: ConfigurationPlatform) : Configuration {
    override val screenWidth: Dp
        get() = configuration.screenWidthDp.dp
    override val screenHeight: Dp
        get() = configuration.screenHeightDp.dp
    override val orientation: Configuration.Orientation
        get() = when (val value = configuration.orientation) {
            ConfigurationPlatform.ORIENTATION_LANDSCAPE -> Configuration.Orientation.LANDSCAPE
            ConfigurationPlatform.ORIENTATION_PORTRAIT -> Configuration.Orientation.PORTRAIT
            ConfigurationPlatform.ORIENTATION_UNDEFINED -> Configuration.Orientation.PORTRAIT // i'm think this is ok
            else -> throw RuntimeException("Unexpected orientation = $value")
        }
}