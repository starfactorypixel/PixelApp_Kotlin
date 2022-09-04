package ru.starfactory.core.bluetooth.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.starfactory.core.bluetooth.domain.BluetoothDevice

class JvmBluetoothService : BluetoothService {

    override fun getIsEnabled(): Boolean = false

    override fun getBoundedDevices(): List<BluetoothDevice> = emptyList()

    override fun observeBoundedDevices(): Flow<List<BluetoothDevice>> {
        return flow { emit(getBoundedDevices()) } // TODO Sumin
    }
}
