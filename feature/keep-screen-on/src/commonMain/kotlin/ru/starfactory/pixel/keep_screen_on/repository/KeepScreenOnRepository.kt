package ru.starfactory.pixel.keep_screen_on.repository

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.key_value_storage.service.KeyValueStorageService

internal interface KeepScreenOnRepository {
    fun observeIsKeepScreenOn(): Flow<Boolean>
    suspend fun setIsKeepScreenOn(isKeepScreenOn: Boolean)
}

internal class KeepScreenOnRepositoryImpl(keyValueStorageService: KeyValueStorageService) : KeepScreenOnRepository {
    private val storage = keyValueStorageService.createStorage("feature-keep-screen-on")
    private val isKeepScreenOnFlow = storage.getBooleanFlow(IS_KEEP_SCREEN_ON)

    override fun observeIsKeepScreenOn(): Flow<Boolean> = isKeepScreenOnFlow
    override suspend fun setIsKeepScreenOn(isKeepScreenOn: Boolean) {
        storage.putBoolean(IS_KEEP_SCREEN_ON, isKeepScreenOn)
    }

    companion object {
        private const val IS_KEEP_SCREEN_ON = "is_keep_screen_on"
    }
}
