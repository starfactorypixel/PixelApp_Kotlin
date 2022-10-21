package ru.starfactory.core.apps.domain

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AndroidAppsInteractor(context: Context) : AppsInteractor {
    private val pm: PackageManager = context.packageManager
    override val isAvailable: Boolean = true

    override suspend fun getApps(): List<AppInfo> = withContext(Dispatchers.IO) {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        pm.queryIntentActivities(intent, 0)
            .map { resolvedInfo ->

                val appInfo = resolvedInfo.activityInfo.applicationInfo
                val appName = pm.getApplicationLabel(appInfo).toString()

                AppInfo(
                    appInfo.packageName,
                    appName,
                )
            }
            .associateBy { it.id }
            .values
            .toList()
    }

    override suspend fun getIcon(appInfo: AppInfo): ImageBitmap = withContext(Dispatchers.IO) {
        val androidAppInfo = pm.getApplicationInfo(appInfo.id, 0)
        pm.getApplicationIcon(androidAppInfo)
            .toBitmap()
            .asImageBitmap()
    }
}
