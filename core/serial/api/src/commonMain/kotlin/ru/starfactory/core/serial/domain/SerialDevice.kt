package ru.starfactory.core.serial.domain

data class SerialDevice(
    val id: SerialDeviceId,
    val type: SerialDeviceType,
    val name: String,
)