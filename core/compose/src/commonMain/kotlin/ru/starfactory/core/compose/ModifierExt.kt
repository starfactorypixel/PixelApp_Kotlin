package ru.starfactory.core.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.paddingSystemWindowInsets() = this.padding(WindowInsets.systemBars.asPaddingValues())
