package ru.starfactory.pixel.domain.bluetooth

import kotlinx.coroutines.flow.Flow
import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import ru.starfactory.pixel.service.bluetooth.BluetoothService

interface BluetoothInteractor {
    fun getIsEnabled(): Boolean
    fun getBoundedDevices(): List<BluetoothDevice>
    fun observeBoundedDevices(): Flow<List<BluetoothDevice>>
}

class BluetoothInteractorImpl(private val bluetoothService: BluetoothService) : BluetoothInteractor {
    override fun getIsEnabled(): Boolean = bluetoothService.getIsEnabled()
    override fun getBoundedDevices(): List<BluetoothDevice> = bluetoothService.getBoundedDevices()
    override fun observeBoundedDevices(): Flow<List<BluetoothDevice>> = bluetoothService.observeBoundedDevices()
}