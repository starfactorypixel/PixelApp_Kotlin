package ru.starfactory.core.apps.domain

import androidx.compose.ui.graphics.ImageBitmap

data class AppInfo(
    val id: String,
    val name: String,
    val icon: ImageBitmap,
)
