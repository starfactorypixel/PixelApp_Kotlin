package ru.starfactory.core.serial.bluetooth.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ru.starfactory.core.bluetooth.domain.BluetoothInteractor
import ru.starfactory.core.serial.domain.SerialDevice
import ru.starfactory.core.serial.domain.SerialDeviceId
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SourceTypeSerialInteractor

interface BluetoothSerialInteractor : SourceTypeSerialInteractor {
    fun isConnectPermissionGranted(): Boolean
    fun observeIsConnectPermissionGranted(): Flow<Boolean>
    suspend fun requestConnectPermission(): Boolean
    override fun observeSerialDevices(): Flow<List<BluetoothSerialDevice>>
}

internal class BluetoothSerialInteractorImpl(
    private val bluetoothInteractor: BluetoothInteractor
) : BluetoothSerialInteractor {
    override val sourceType: SerialDeviceType = SerialDeviceType.BLUETOOTH

    override fun observeSerialDevices(): Flow<List<BluetoothSerialDevice>> {
        return bluetoothInteractor.observeBoundedDevices()
            .map { devices ->
                devices.map { device ->
                    BluetoothSerialDevice(
                        SerialDeviceId(SerialDeviceType.BLUETOOTH, device.address),
                        SerialDeviceType.BLUETOOTH,
                        name = device.name,
                    )
                }
            }
    }

    override fun isConnectPermissionGranted(): Boolean = bluetoothInteractor.isConnectPermissionGranted()
    override fun observeIsConnectPermissionGranted(): Flow<Boolean> = bluetoothInteractor.observeIsConnectPermissionGranted()
    override suspend fun requestConnectPermission(): Boolean = bluetoothInteractor.requestConnectPermission()
}