package ru.starfactory.pixel.dashboard_screen.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.view.switch.POutlinedSwitch

@Composable
internal fun FastActionsView(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            "Fast Actions",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.W600,
        )
        Spacer(Modifier.padding(vertical = 4.dp))
        Row {
            FastAction("Action #1")
            FastAction("Action #2")
            FastAction("Action #3")
        }

        Row {
            FastAction("Action #4")
            FastAction("Action #5")
            FastAction("Action #6")
        }
    }
}

@Composable
private fun FastAction(text: String) {
    var isSelected by remember { mutableStateOf(false) }

    POutlinedSwitch(
        isSelected = isSelected,
        onClick = { isSelected = !isSelected },
        Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(text)
    }
}