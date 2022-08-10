package ru.starfactory.pixel.service.bluetooth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BluetoothDevice(
    val address: String,
    val name: String,
    val connectionState: ConnectionState
) : Parcelable {
    enum class ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
    }
}
