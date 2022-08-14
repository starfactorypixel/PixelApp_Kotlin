package ru.starfactory.pixel.ui.screen.main_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ru.starfactory.core.navigation.ui.LocalNavigation

@Composable
fun MainMenuView(
    onCloseRequest: () -> Unit = {}
) {
    val navigation = LocalNavigation.current

    MainMenuContent(
        onClickDebug = {
//            navigation.push(DebugScreen)
            onCloseRequest()
        },
        onClickSettings = {
//            navigation.push(SettingsScreen)
            onCloseRequest()
        }
    )
}

@Composable
private fun MainMenuContent(
    onClickDebug: () -> Unit = {},
    onClickSettings: () -> Unit = {}
) {
    Column {
        Divider()
//        MenuButton(text = "Debug", icon = painterResource(id = R.drawable.ic_debug), onClickDebug)
//        MenuButton(text = "Settings", icon = painterResource(id = R.drawable.ic_settings), onClickSettings)
    }
}

@Composable
private fun MenuButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp, 8.dp)
            .defaultMinSize(minHeight = 32.dp)
    ) {

        Icon(
            painter = icon,
            contentDescription = null,
            Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
    Divider()
}