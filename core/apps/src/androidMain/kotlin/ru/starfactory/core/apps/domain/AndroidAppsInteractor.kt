package ru.starfactory.core.apps.domain

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap

internal class AndroidAppsInteractor(context: Context) : AppsInteractor {
    private val pm: PackageManager = context.packageManager
    override val isAvailable: Boolean = true

    override suspend fun getApps(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        return pm.queryIntentActivities(intent, 0)
            .map { resolvedInfo ->

                val appInfo = resolvedInfo.activityInfo.applicationInfo
                val appName = pm.getApplicationLabel(appInfo).toString()

                val appIcon = pm.getApplicationIcon(appInfo)
                    .toBitmap()
                    .asImageBitmap()

                AppInfo(
                    appInfo.packageName,
                    appName,
                    appIcon,
                )
            }
            .associateBy { it.id }
            .values
            .toList()
    }
}
