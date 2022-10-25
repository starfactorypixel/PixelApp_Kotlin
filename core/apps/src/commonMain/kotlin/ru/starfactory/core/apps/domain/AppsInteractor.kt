package ru.starfactory.core.apps.domain

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.Flow

interface AppsInteractor {
    /**
     * true if AppsInteractor supported for current platform
     */
    val isAvailable: Boolean

    suspend fun getApps(): List<AppInfo>
    fun observeApps(): Flow<List<AppInfo>>
    suspend fun getIcon(appInfo: AppInfo): ImageBitmap
    fun launchApp(appInfo: AppInfo)
}
