package ru.starfactory.pixel.service.bluetooth

data class BluetoothDevice(
    val address: String,
    val name: String,
    val connectionState: ConnectionState
) {
    enum class ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
    }
}
