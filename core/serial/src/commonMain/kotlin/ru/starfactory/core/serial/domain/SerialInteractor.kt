package ru.starfactory.core.serial.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

interface SerialInteractor {
    fun observeSerialDevices(): Flow<List<SerialDevice>>
    suspend fun connect(serialDevice: SerialDevice, block: suspend CoroutineScope.(SerialConnection) -> Unit)
}

internal class SerialInteractorImpl(
    providersSet: Set<Pair<String, SerialDevicesProvider>>,
) : SerialInteractor {
    private val providers = providersSet.toMap()
    override fun observeSerialDevices(): Flow<List<SerialDevice>> {
        return combine(
            providers.map { (providerId, provider) -> provider.observeSerialDevices(providerId) }
        ) { sources -> sources.flatMap { it } }
    }

    override suspend fun connect(serialDevice: SerialDevice, block: suspend CoroutineScope.(SerialConnection) -> Unit) {
        providers[serialDevice.providerId]!!.connect(serialDevice.deviceId, block)
    }
}

private fun SerialDevicesProvider.observeSerialDevices(providerId: String): Flow<List<SerialDevice>> {
    return observeSerialDevicesInfo()
        .map { devices ->
            devices.map { (deviceId, deviceInfo) ->
                SerialDevice(
                    providerId = providerId,
                    deviceId = deviceId,
                    info = deviceInfo
                )
            }
        }
}
