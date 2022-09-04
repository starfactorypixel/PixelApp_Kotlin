package ru.starfactory.pixel.ecu_connection.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.key_value_storage.service.KeyValueStorageService

internal interface EcuSourceRepository {
    fun observeSelectedSourceId(): Flow<String?>
    suspend fun saveSelectedSourceId(sourceId: String)
}

internal class EcuSourceRepositoryImpl(keyValueStorageService: KeyValueStorageService) : EcuSourceRepository {
    private val storage = keyValueStorageService.createStorage("feature-ecu-connection-source")

    val source = storage.getStringOrNullFlow(SOURCE_KEY)

    override fun observeSelectedSourceId(): Flow<String?> = source

    override suspend fun saveSelectedSourceId(sourceId: String) {
        storage.putString(SOURCE_KEY, sourceId)
    }

    companion object {
        private const val SOURCE_KEY = "source"
    }
}
