package ru.starfactory.core.uikit.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.starfactory.core.uikit.theme.PixelTheme

@Composable
fun PWSettingsView(
    screenName: String,
    modifier: Modifier = Modifier,
    onClickClose: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(modifier) {

        Column(
            Modifier
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            Text(
                screenName,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                style = PixelTheme.typography.h6,
                fontWeight = FontWeight.W600,
            )
            content()
        }

        PWBottomMenuAction(
            text = "Close",
            icon = Icons.Default.Close,
            Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            orientation = PWBottomMenuActionOrientation.Vertical,
            onClick = onClickClose,
            borderColor = PixelTheme.colors.error
        )
    }
}
