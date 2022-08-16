package ru.starfactory.core.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable

val WindowInsets.Companion.systemBars: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = getSystemBarInsets()

@Composable
internal expect fun getSystemBarInsets(): WindowInsets