package ru.starfactory.core.serial.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface SerialInteractor {
    fun observeSerialDevices(): Flow<List<SerialDevice>>
}

internal class SerialInteractorImpl(
    private val typedSerialInteractors: Set<SourceTypeSerialInteractor>,
) : SerialInteractor {
    override fun observeSerialDevices(): Flow<List<SerialDevice>> {
        return combine(typedSerialInteractors.map { it.observeSerialDevices() }) { sources ->
            sources.flatMap { it }
        }
    }
}