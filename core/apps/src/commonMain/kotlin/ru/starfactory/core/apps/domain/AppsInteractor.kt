package ru.starfactory.core.apps.domain

interface AppsInteractor {
    /**
     * true if AppsInteractor supported for current platform
     */
    val isAvailable: Boolean

    suspend fun getApps(): List<AppInfo>
}
