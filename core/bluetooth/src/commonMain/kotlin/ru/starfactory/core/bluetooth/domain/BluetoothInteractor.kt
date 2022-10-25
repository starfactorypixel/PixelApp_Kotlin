package ru.starfactory.core.bluetooth.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import ru.starfactory.core.bluetooth.service.BluetoothService
import ru.starfactory.core.permission.domain.PermissionInteractor
import ru.starfactory.core.permission.service.Permission
import java.util.UUID

interface BluetoothInteractor {
    fun isConnectPermissionGranted(): Boolean
    fun observeIsConnectPermissionGranted(): Flow<Boolean>
    suspend fun requestConnectPermission(): Boolean
    fun getIsEnabled(): Boolean
    fun getBoundedDevices(): List<BluetoothDevice>
    fun observeBoundedDevices(): Flow<List<BluetoothDevice>>

    suspend fun connect(address: String, channelId: UUID, block: suspend (BluetoothService.BluetoothConnection) -> Unit)
}

internal class BluetoothInteractorImpl(
    private val bluetoothService: BluetoothService,
    private val permissionInteractor: PermissionInteractor,
) : BluetoothInteractor {
    override fun isConnectPermissionGranted(): Boolean =
        permissionInteractor.getIsPermissionGranted(Permission.BLUETOOTH_CONNECT)

    override fun observeIsConnectPermissionGranted(): Flow<Boolean> =
        permissionInteractor.observeIsPermissionGranted(Permission.BLUETOOTH_CONNECT)

    override suspend fun requestConnectPermission(): Boolean =
        permissionInteractor.requestPermission(Permission.BLUETOOTH_CONNECT)

    override fun getIsEnabled(): Boolean = bluetoothService.getIsEnabled()

    override fun getBoundedDevices(): List<BluetoothDevice> {
        return if (isConnectPermissionGranted()) bluetoothService.getBoundedDevices()
        else emptyList()
    }

    override fun observeBoundedDevices(): Flow<List<BluetoothDevice>> {
        return observeIsConnectPermissionGranted().flatMapLatest {
            if (it) bluetoothService.observeBoundedDevices()
            else flowOf(emptyList())
        }
    }

    override suspend fun connect(
        address: String,
        channelId: UUID,
        block: suspend (BluetoothService.BluetoothConnection) -> Unit
    ) {
        return bluetoothService.connect(address, channelId, block)
    }
}
