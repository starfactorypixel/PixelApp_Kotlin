package ru.starfactory.pixel.ecu_connection.domain.connection.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuPrimaryState
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor

internal interface EcuDemoSourceConnectionInteractor : EcuSourceConnectionInteractor

internal class EcuDemoSourceConnectionInteractorImpl : EcuDemoSourceConnectionInteractor {
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
