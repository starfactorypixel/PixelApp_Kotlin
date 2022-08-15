package ru.starfactory.core.key_value_storage

import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.key_value_storage.service.FlowSettingsFactory
import ru.starfactory.core.key_value_storage.service.KeyValueStorageService
import ru.starfactory.core.key_value_storage.service.KeyValueStorageServiceImpl

fun Modules.coreKeyValueStorage() = DI.Module("core-key-value-storage") {
    bindSingleton { createSettingsFactory() }
    bindSingleton<KeyValueStorageService> { KeyValueStorageServiceImpl(i()) }
}

internal expect fun DirectDI.createSettingsFactory(): FlowSettingsFactory