package ru.starfactory.pixel.ecu_connection

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import ru.starfactory.core.di.Modules
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuConnectionInteractorImpl
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractorImpl

fun Modules.featureEcuConnection() = DI.Module("feature-ecu-connection") {
    bindSingleton<EcuConnectionInteractor> { EcuConnectionInteractorImpl() }
    bindSingleton<EcuSourceInteractor> { EcuSourceInteractorImpl() }
}