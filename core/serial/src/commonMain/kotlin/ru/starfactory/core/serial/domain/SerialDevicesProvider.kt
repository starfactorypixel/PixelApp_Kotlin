package ru.starfactory.core.serial.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface SerialDevicesProvider {
    fun observeSerialDevicesInfo(): Flow<Map<String, SerialDeviceInfo>>
    suspend fun connect(id: String, block: suspend CoroutineScope.(SerialConnection) -> Unit)
}
