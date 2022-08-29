package ru.starfactory.core.serial.domain

import kotlinx.coroutines.flow.Flow

interface SourceTypeSerialInteractor {
    val sourceType: SerialDeviceType
    fun observeSerialDevices(): Flow<List<SerialDevice>>
}