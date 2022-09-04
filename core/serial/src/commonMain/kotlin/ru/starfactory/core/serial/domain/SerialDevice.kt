package ru.starfactory.core.serial.domain

data class SerialDevice(
    val providerId: String,
    val deviceId: String,
    val info: SerialDeviceInfo
) {
    val id: String = "$providerId@$deviceId"
}