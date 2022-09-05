package ru.starfactory.core.bluetooth.domain

import java.util.UUID

data class BluetoothDevice(
    val address: String,
    val name: String,
    val channels: List<UUID>,
)
