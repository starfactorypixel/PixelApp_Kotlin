package ru.starfactory.core.serial.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.starfactory.core.coroutines.shareDefault

interface SerialInteractor {
    fun observeSerialDevices(): Flow<List<SerialDevice>>
    fun observeSerialDevice(id: String): Flow<SerialDevice?>
    suspend fun connect(serialDevice: SerialDevice, block: suspend (SerialConnection) -> Unit)
}

internal class SerialInteractorImpl(
    providersSet: Set<Pair<String, SerialDevicesProvider>>,
    private val scope: CoroutineScope,
) : SerialInteractor {
    private val providers = providersSet.toMap()

    private val serialDevicesObservable: Flow<List<SerialDevice>> =
        combine(
            providers.map { (providerId, provider) -> provider.observeSerialDevices(providerId) }
        ) { sources -> sources.flatMap { it } }
            .shareDefault(scope)

    override fun observeSerialDevices(): Flow<List<SerialDevice>> = serialDevicesObservable

    override fun observeSerialDevice(id: String): Flow<SerialDevice?> {
        return serialDevicesObservable
            .map { devices -> devices.find { it.id == id } }
            .distinctUntilChanged()
    }

    override suspend fun connect(serialDevice: SerialDevice, block: suspend (SerialConnection) -> Unit) {
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
