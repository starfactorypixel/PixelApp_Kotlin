package ru.starfactory.core.key_value_storage


import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import org.kodein.di.DirectDI
import ru.starfactory.core.di.i
import ru.starfactory.core.key_value_storage.service.FlowSettingsFactory

internal actual fun DirectDI.createSettingsFactory(): FlowSettingsFactory {
    val factory = AndroidSettings.Factory(i())
    return FlowSettingsFactory { factory.create(it) as ObservableSettings }
}