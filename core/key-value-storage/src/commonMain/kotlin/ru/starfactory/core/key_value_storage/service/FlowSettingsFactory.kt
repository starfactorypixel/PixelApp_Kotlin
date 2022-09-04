package ru.starfactory.core.key_value_storage.service

import com.russhwolf.settings.ObservableSettings

internal fun interface FlowSettingsFactory {
    fun createSettings(name: String): ObservableSettings
}
