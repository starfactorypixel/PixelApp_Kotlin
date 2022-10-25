package ru.starfactory.core.apps.domain

import androidx.compose.ui.graphics.ImageBitmap

internal class JvmAppsInteractor : AppsInteractor {
    override val isAvailable: Boolean = false

    override suspend fun getApps(): List<AppInfo> = error()
    override suspend fun getIcon(appInfo: AppInfo): ImageBitmap = error()
    override fun launchApp(appInfo: AppInfo) = error()

    private fun error(): Nothing {
        throw NotImplementedError("AppsInteractor not supported for jvm")
    }
}
