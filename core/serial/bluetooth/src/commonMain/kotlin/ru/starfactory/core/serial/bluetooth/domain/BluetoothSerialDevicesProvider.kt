package ru.starfactory.core.serial.bluetooth.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.starfactory.core.bluetooth.domain.BluetoothInteractor
import ru.starfactory.core.serial.domain.SerialConnection
import ru.starfactory.core.serial.domain.SerialDeviceType
import ru.starfactory.core.serial.domain.SerialDevicesProvider
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

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
                devices
                    .filter { SERIAL_UUID in it.channels }
                    .associate { device ->
                        device.address to BluetoothSerialDeviceInfo(
                            SerialDeviceType.BLUETOOTH,
                            name = device.name,
                        )
                    }
            }
    }

    override suspend fun connect(id: String, block: suspend CoroutineScope.(SerialConnection) -> Unit) {
        bluetoothInteractor.connect(id, SERIAL_UUID) {
            val serialConnection = object : SerialConnection {
                override val inputStream: InputStream = it.inputStream
                override val outputStream: OutputStream = it.outputStream
            }

            block(serialConnection)
        }
    }

    override fun isConnectPermissionGranted(): Boolean = bluetoothInteractor.isConnectPermissionGranted()
    override fun observeIsConnectPermissionGranted(): Flow<Boolean> = bluetoothInteractor.observeIsConnectPermissionGranted()
    override suspend fun requestConnectPermission(): Boolean = bluetoothInteractor.requestConnectPermission()

    companion object {
        private val SERIAL_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }
}
