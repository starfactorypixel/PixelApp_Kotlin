package ru.starfactory.core.serial.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

interface SerialInteractor {
    fun observeSerialDevices(): Flow<List<SerialDevice>>
}

internal class SerialInteractorImpl(
    private val providers: Set<Pair<String, SerialDevicesProvider>>,
) : SerialInteractor {
    override fun observeSerialDevices(): Flow<List<SerialDevice>> {
        return combine(
            providers.map { (providerId, provider) -> provider.observeSerialDevices(providerId) }
        ) { sources -> sources.flatMap { it } }
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
