package ru.starfactory.pixel.service.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice as AndroidBluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import ru.starfactory.pixel.Tag
import java.io.InputStream
import java.io.OutputStream
import java.util.*

interface BluetoothService {
    fun getIsEnabled(): Boolean
    fun getBoundedDevices(): List<BluetoothDevice>
    fun observeBoundedDevices(): Flow<List<BluetoothDevice>>
    suspend fun connect(address: String, channelId: UUID, block: suspend CoroutineScope.(BluetoothConnection) -> Unit)

    interface BluetoothConnection {
        val inputStream: InputStream
        val outputStream: OutputStream
    }
}

class BluetoothServiceImpl(context: Context) : BluetoothService {
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter

    override fun getIsEnabled(): Boolean = bluetoothAdapter.isEnabled

    @SuppressLint("MissingPermission")
    override fun getBoundedDevices(): List<BluetoothDevice> = bluetoothAdapter.bondedDevices.toBluetoothDevices()

    override fun observeBoundedDevices(): Flow<List<BluetoothDevice>> {
        return flowOf(getBoundedDevices()) // TODO
    }

    @SuppressLint("MissingPermission")
    override suspend fun connect(
        address: String,
        channelId: UUID,
        block: suspend CoroutineScope.(BluetoothService.BluetoothConnection) -> Unit
    ) = withContext(Dispatchers.IO) {
            val device = bluetoothAdapter.getRemoteDevice(address)
            println(device.address)
            device.createRfcommSocketToServiceRecord(channelId).use { channel ->
                channel.connect()

                val connection = object : BluetoothService.BluetoothConnection {
                    override val inputStream: InputStream = channel.inputStream
                    override val outputStream: OutputStream = channel.outputStream
                }
                coroutineScope {
                    block(this, connection)
                }
            }
        }

    companion object {
        private const val TAG = Tag.BLUETOOTH
    }
}

private fun Set<AndroidBluetoothDevice>.toBluetoothDevices() = map { it.toBluetoothDevice() }

@SuppressLint("MissingPermission")
private fun AndroidBluetoothDevice.toBluetoothDevice() = BluetoothDevice(
    address = this.address,
    name = this.name,
    connectionState = when (val boundState = this.bondState) {
        AndroidBluetoothDevice.BOND_NONE -> BluetoothDevice.ConnectionState.CONNECTED
        AndroidBluetoothDevice.BOND_BONDING -> BluetoothDevice.ConnectionState.CONNECTING
        AndroidBluetoothDevice.BOND_BONDED -> BluetoothDevice.ConnectionState.DISCONNECTED
        else -> throw IllegalStateException("Unknown boundState=$boundState")
    }
)