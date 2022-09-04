package ru.starfactory.core.bluetooth.service

import kotlinx.coroutines.flow.Flow
import ru.starfactory.core.bluetooth.domain.BluetoothDevice
import java.util.*

interface BluetoothService {
    fun getIsEnabled(): Boolean
    fun getBoundedDevices(): List<BluetoothDevice>
    fun observeBoundedDevices(): Flow<List<BluetoothDevice>>
//    suspend fun connect(address: String, channelId: UUID, block: suspend CoroutineScope.(BluetoothConnection) -> Unit)

//    interface BluetoothConnection {
//        val inputStream: InputStream
//        val outputStream: OutputStream
//    }
}
