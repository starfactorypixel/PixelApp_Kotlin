package ru.starfactory.pixel.main_screen.ui.widged

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.starfactory.core.compose.Configuration
import ru.starfactory.core.compose.LocalConfiguration
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.widget.PWBottomMenuAction
import ru.starfactory.core.uikit.widget.PWBottomMenuActionOrientation

@Composable
internal fun BottomActionsView(
    modifier: Modifier = Modifier,
    onClickSettings: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val isSmall = configuration.screenSize <= Configuration.ScreenSize.Phone &&
        configuration.orientation == Configuration.Orientation.PORTRAIT

    Column(modifier) {
        Row(verticalAlignment = Alignment.Bottom) {
            if (!isSmall) PWBottomMenuAction("Settings", Icons.Default.Settings, onClick = onClickSettings)
            Spacer(Modifier.weight(1f))
            BottomActionFlat("Left belt")
            BottomActionFlat("Front")
            BottomActionFlat("Airflow")
            BottomActionFlat("*****")
            BottomActionFlat("Right belt")
            Spacer(Modifier.weight(1f))
            if (!isSmall) PWBottomMenuAction("Power off", Icons.Default.Power, borderColor = PixelTheme.colors.error)
        }
        if (isSmall) Row {
            PWBottomMenuAction(
                "Settings",
                Icons.Default.Settings,
                Modifier.weight(1f),
                orientation = PWBottomMenuActionOrientation.HorizontalStart,
                onClick = onClickSettings
            )

            Spacer(Modifier.width(8.dp))

            PWBottomMenuAction(
                "Power off",
                Icons.Default.Power,
                Modifier.weight(1f),
                orientation = PWBottomMenuActionOrientation.HorizontalEnd,
                borderColor = PixelTheme.colors.error
            )
        }
    }
}

@Composable
private fun BottomActionFlat(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier
            .padding(horizontal = 8.dp)
            .clip(PixelTheme.shapes.medium)
            .clickable { /* TODO */ }
    ) {
        Column(Modifier.padding(8.dp)) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text,
                Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
