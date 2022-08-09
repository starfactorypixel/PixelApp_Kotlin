package ru.starfactory.pixel.domain.bluetooth

import ru.starfactory.pixel.service.bluetooth.BluetoothDevice
import ru.starfactory.pixel.service.bluetooth.BluetoothService

interface BluetoothInteractor {
    fun getIsEnabled(): Boolean
    fun getBoundedDevices(): List<BluetoothDevice>
}

class BluetoothInteractorImpl(private val bluetoothService: BluetoothService) : BluetoothInteractor {
    override fun getIsEnabled(): Boolean = bluetoothService.getIsEnabled()
    override fun getBoundedDevices(): List<BluetoothDevice> = bluetoothService.getBoundedDevices()
}