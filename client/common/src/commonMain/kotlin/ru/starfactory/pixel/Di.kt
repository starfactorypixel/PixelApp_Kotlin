package ru.starfactory.pixel

import org.kodein.di.*
import ru.starfactory.core.apps.coreApps
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.core.key_value_storage.coreKeyValueStorage
import ru.starfactory.feature.apps.featureApps
import ru.starfactory.pixel.dashboard_screen.featureDashboardScreen
import ru.starfactory.pixel.ecu_connection.featureEcuConnection
import ru.starfactory.pixel.main_screen.featureMainScreen
import ru.starfactory.pixel.theming.featureTheming
import ru.starfactory.pixel.ui.screen.root.RootViewModel

@Suppress("UnusedReceiverParameter")
fun Modules.mainCommonModule() = DI.Module("main-common-module") {
    // Core
    importOnce(Modules.coreApps())
    importOnce(Modules.coreKeyValueStorage())

    // Feature
    importOnce(Modules.featureApps())
    importOnce(Modules.featureEcuConnection())
    importOnce(Modules.featureDashboardScreen())
    importOnce(Modules.featureMainScreen())
    importOnce(Modules.featureTheming())

    // View Models
    bindProvider { RootViewModel() }
}