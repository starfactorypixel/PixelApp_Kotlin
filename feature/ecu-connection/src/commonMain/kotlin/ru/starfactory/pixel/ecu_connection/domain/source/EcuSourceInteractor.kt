package ru.starfactory.pixel.ecu_connection.domain.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.retry
import ru.starfactory.pixel.ecu_connection.domain.connection.EcuSourceConnectionInteractor
import ru.starfactory.pixel.ecu_connection.domain.connection.demo.EcuDemoSourceConnectionInteractor

interface EcuSourceInteractor {
    fun observeSources(): Flow<List<Source>>
    fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor>
}

internal class EcuSourceInteractorImpl(
    private val ecuDemoSourceConnectionInteractor: EcuDemoSourceConnectionInteractor,
) : EcuSourceInteractor {
    override fun observeSources(): Flow<List<Source>> {
        return flowOf(listOf(Source.Demo))
    }

    override fun observeDefaultSourceConnectionInteractor(): Flow<EcuSourceConnectionInteractor> {
        return flowOf(ecuDemoSourceConnectionInteractor)
    }
}