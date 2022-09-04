package ru.starfactory.core.key_value_storage.service

import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.Dispatchers

interface KeyValueStorageService {
    fun createStorage(name: String): FlowSettings
}

internal class KeyValueStorageServiceImpl(
    private val factory: FlowSettingsFactory
) : KeyValueStorageService {
    override fun createStorage(name: String): FlowSettings {
        return factory.createSettings(name).toFlowSettings(Dispatchers.IO)
    }
}
