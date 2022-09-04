package ru.starfactory.pixel.ecu_connection.domain.connection

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import ru.starfactory.pixel.ecu_connection.domain.source.EcuSourceInteractor

interface EcuDefaultSourceConnectionInteractor : EcuSourceConnectionInteractor

internal class EcuDefaultSourceConnectionInteractorImpl(ecuSourceInteractor: EcuSourceInteractor) :
    EcuDefaultSourceConnectionInteractor {

    private val defaultSource = ecuSourceInteractor.observeDefaultSourceConnectionInteractor()

    override fun observePrimaryState(): Flow<EcuPrimaryState> = defaultSource.flatMapLatest { it.observePrimaryState() }
}
