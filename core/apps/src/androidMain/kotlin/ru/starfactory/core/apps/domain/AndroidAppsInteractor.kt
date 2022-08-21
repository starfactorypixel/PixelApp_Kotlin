package ru.starfactory.core.apps.domain

import android.content.Context
import android.content.pm.PackageManager

internal class AndroidAppsInteractor(context: Context) : AppsInteractor {
    private val packageManager: PackageManager = context.packageManager
    override val isAvailable: Boolean = true

    override suspend fun getApps(): List<AppInfo> {
        return packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
            .map { AppInfo(it.packageName, it.applicationInfo.name) }
    }
}

