package ru.starfactory.pixel.keep_screen_on.ui

import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun KeepScreenOnView(viewModel: KeepScreenOnViewModel) {
    val window = (LocalContext.current as Activity).window

    val isKeepScreenOn by viewModel.state.collectAsState()

    LaunchedEffect(isKeepScreenOn) {
        if (isKeepScreenOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}
