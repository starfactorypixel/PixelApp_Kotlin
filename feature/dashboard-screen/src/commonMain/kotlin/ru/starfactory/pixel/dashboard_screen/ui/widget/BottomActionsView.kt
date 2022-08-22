package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.view.POutlinedFloatingActionButton
import ru.starfactory.core.uikit.view.PTextFloatingActionButton

@Composable
internal fun BottomActionsView(
    onClickSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier.padding(horizontal = 8.dp)) {
        BottomAction("Settings", onClickSettings)
        Spacer(Modifier.weight(1f))
        BottomActionFlat("Left belt")
        BottomActionFlat("Front")
        BottomActionFlat("Airflow")
        BottomActionFlat("*****")
        BottomActionFlat("Right belt")
        Spacer(Modifier.weight(1f))
        BottomAction("Power off") {}
    }
}

@Composable
private fun BottomAction(text: String, onClick: () -> Unit) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        POutlinedFloatingActionButton(onClick = onClick) {
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