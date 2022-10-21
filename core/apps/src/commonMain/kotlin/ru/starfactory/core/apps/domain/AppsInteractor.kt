package ru.starfactory.core.apps.domain

import androidx.compose.ui.graphics.ImageBitmap

interface AppsInteractor {
    /**
     * true if AppsInteractor supported for current platform
     */
    val isAvailable: Boolean

    suspend fun getApps(): List<AppInfo>
    suspend fun getIcon(appInfo: AppInfo): ImageBitmap
}
