package ru.starfactory.pixel.ecu_connection

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.core.di.i
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractorImpl
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceConnectionInteractorFactory
import ru.starfactory.pixel.ecu_connection.domain.connection.serial.EcuSerialSourceConnectionInteractorFactoryImpl
import ru.starfactory.pixel.ecu_connection.domain.repository.EcuSourceRepository
import ru.starfactory.pixel.ecu_connection.domain.repository.EcuSourceRepositoryImpl
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractorImpl
import ru.starfactory.pixel.ecu_connection.ui.screen.select_source.SelectSourceViewModel

fun Modules.featureEcuConnection() = DI.Module("feature-ecu-connection") {
    bindSingleton<EcuSourceRepository> { EcuSourceRepositoryImpl(i()) }
    bindSingleton<EcuDemoSourceConnectionInteractor> { EcuDemoSourceConnectionInteractorImpl() }
    bindSingleton<EcuSerialSourceConnectionInteractorFactory> { EcuSerialSourceConnectionInteractorFactoryImpl() }
    bindSingleton<EcuSourceInteractor> { EcuSourceInteractorImpl(i(), i(), i(), i(), i()) }
    bindProvider { SelectSourceViewModel(i(), i()) }
}
