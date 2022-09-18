package ru.starfactory.core.bluetooth.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.starfactory.core.bluetooth.domain.BluetoothDevice
import java.util.UUID

class JvmBluetoothService : BluetoothService {

    override fun getIsEnabled(): Boolean = false

    override fun getBoundedDevices(): List<BluetoothDevice> = emptyList()

    override fun observeBoundedDevices(): Flow<List<BluetoothDevice>> {
        return flow { emit(getBoundedDevices()) } // TODO Sumin
    }

    override suspend fun connect(
        address: String,
        channelId: UUID,
        block: suspend CoroutineScope.(BluetoothService.BluetoothConnection) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
