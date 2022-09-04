package ru.starfactory.pixel.main_screen.ui.widged

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme
import ru.starfactory.core.uikit.view.PTextFloatingActionButton
import ru.starfactory.core.uikit.widget.PWBottomMenuAction

@Composable
internal fun BottomActionsView(
    modifier: Modifier = Modifier,
    onClickSettings: () -> Unit = {},
) {
    Row(modifier) {
        PWBottomMenuAction("Settings", Icons.Default.Settings, onClick = onClickSettings)
        Spacer(Modifier.weight(1f))
        BottomActionFlat("Left belt")
        BottomActionFlat("Front")
        BottomActionFlat("Airflow")
        BottomActionFlat("*****")
        BottomActionFlat("Right belt")
        Spacer(Modifier.weight(1f))
        PWBottomMenuAction("Power off", Icons.Default.Power, borderColor = PixelTheme.colors.error)
    }
}

@Composable
private fun BottomActionFlat(text: String) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        PTextFloatingActionButton(onClick = {}) {
            Icon(Icons.Default.Settings, null, Modifier.size(32.dp))
        }
        Text(
            text,
            Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
