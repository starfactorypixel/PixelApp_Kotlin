package ru.starfactory.core.key_value_storage


import com.russhwolf.settings.JvmPreferencesSettings
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import org.kodein.di.DirectDI
import ru.starfactory.core.key_value_storage.service.FlowSettingsFactory

internal actual fun DirectDI.createSettingsFactory(): FlowSettingsFactory {
    val factory = JvmPreferencesSettings.Factory()
    return FlowSettingsFactory {
        factory.create(it) as ObservableSettings
    }
}