package ru.starfactory.pixel.service.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import ru.starfactory.pixel.Tag

interface BluetoothService {
    fun getIsEnabled(): Boolean
}

class BluetoothServiceImpl(context: Context) : BluetoothService {
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter

    override fun getIsEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }

    companion object {
        private const val TAG = Tag.BLUETOOTH
    }
}