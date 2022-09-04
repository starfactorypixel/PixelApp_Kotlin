package ru.starfactory.core.serial.bluetooth.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.starfactory.core.bluetooth.domain.BluetoothInteractor
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialDevicesProvider

interface BluetoothSerialDevicesProvider : SerialDevicesProvider {
    fun isConnectPermissionGranted(): Boolean
    fun observeIsConnectPermissionGranted(): Flow<Boolean>
    suspend fun requestConnectPermission(): Boolean
    override fun observeSerialDevicesInfo(): Flow<Map<String, BluetoothSerialDeviceInfo>>
}

internal class BluetoothSerialDevicesProviderImpl(
    private val bluetoothInteractor: BluetoothInteractor
) : BluetoothSerialDevicesProvider {

    override fun observeSerialDevicesInfo(): Flow<Map<String, BluetoothSerialDeviceInfo>> {
        return bluetoothInteractor.observeBoundedDevices()
            .map { devices ->
                devices.associate { device ->
                    device.address to BluetoothSerialDeviceInfo(
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
