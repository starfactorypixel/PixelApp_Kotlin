package ru.starfactory.pixel.ecu_connection.domain.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface EcuSourceInteractor {
    fun observeSources(): Flow<List<Source>>
}

internal class EcuSourceInteractorImpl : EcuSourceInteractor {
    override fun observeSources(): Flow<List<Source>> {
        return flowOf(listOf(Source.Demo))
    }
}