package ru.starfactory.pixel.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val closeDrawer: () -> Unit = {
        scope.launch { scaffoldState.drawerState.close() }
    }

//    BackHandler(onBack = closeDrawer, enabled = scaffoldState.drawerState.isOpen)

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { /*MainMenuView(onCloseRequest = closeDrawer)*/ }
    ) {
        Box(Modifier.padding(it)) {
            IconButton(
                onClick = { scope.launch { scaffoldState.drawerState.open() } },
                modifier = Modifier.padding(4.dp),
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
            //DashboardScreen()
        }
    }
}