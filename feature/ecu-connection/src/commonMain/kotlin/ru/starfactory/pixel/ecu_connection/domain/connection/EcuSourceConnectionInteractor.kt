package ru.starfactory.pixel.ecu_connection.domain.connection

import kotlinx.coroutines.flow.Flow

interface EcuSourceConnectionInteractor {
//    fun observeConnectionState()
    fun observePrimaryState(): Flow<EcuPrimaryState>
}
