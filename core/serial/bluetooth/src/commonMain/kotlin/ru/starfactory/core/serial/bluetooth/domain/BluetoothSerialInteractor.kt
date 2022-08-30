package ru.starfactory.core.serial.bluetooth.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialDeviceId
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SourceTypeSerialInteractor

interface BluetoothSerialInteractor : SourceTypeSerialInteractor {
    fun observeIsPermissionGranted(): Flow<Boolean>
    suspend fun requestPermission(): Boolean
    override fun observeSerialDevices(): Flow<List<BluetoothSerialDevice>>
}

internal class BluetoothSerialInteractorImpl : BluetoothSerialInteractor {
    override val sourceType: SerialDeviceType = SerialDeviceType.BLUETOOTH

    private val isPermissionGranted = MutableStateFlow(false)

    override fun observeSerialDevices(): Flow<List<BluetoothSerialDevice>> {
        return flowOf(
            listOf(
                BluetoothSerialDevice(
                    id = SerialDeviceId(SerialDeviceType.BLUETOOTH, "var/1"),
                    type = SerialDeviceType.BLUETOOTH,
                    name = "Fake Bluetooth"
                ),
                BluetoothSerialDevice(
                    id = SerialDeviceId(SerialDeviceType.BLUETOOTH, "var/2"),
                    type = SerialDeviceType.BLUETOOTH,
                    name = "Fake Bluetooth 2"
                ),
            )
        )
    }

    override fun observeIsPermissionGranted(): Flow<Boolean> {
        return isPermissionGranted
    }

    override suspend fun requestPermission(): Boolean {
        isPermissionGranted.value = true
        return true
    }
}