package ru.starfactory.core.apps.domain

internal class JvmAppsInteractor : AppsInteractor {
    override val isAvailable: Boolean = false

    override suspend fun getApps(): List<AppInfo> {
        error()
    }

    private fun error(): Nothing {
        throw NotImplementedError("AppsInteractor not supported for jvm")
    }
}
