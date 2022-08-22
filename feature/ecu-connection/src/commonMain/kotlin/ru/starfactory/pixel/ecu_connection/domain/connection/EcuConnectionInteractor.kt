package ru.starfactory.pixel.ecu_connection.domain.connection

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface EcuConnectionInteractor {
    fun observePrimaryState(): Flow<EcuPrimaryState>
}

internal class EcuConnectionInteractorImpl : EcuConnectionInteractor {
    override fun observePrimaryState(): Flow<EcuPrimaryState> {
        return flow {

            while (true) {
                (0..99).forEach {
                    emit(EcuPrimaryState(it, it.toFloat()))
                    delay(100)
                }
                (99 downTo 0).forEach {
                    emit(EcuPrimaryState(it, it.toFloat()))
                    delay(100)
                }
            }

        }
    }
}