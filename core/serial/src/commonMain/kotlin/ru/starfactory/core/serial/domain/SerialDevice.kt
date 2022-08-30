package ru.starfactory.core.serial.domain

interface SerialDevice {
    val id: SerialDeviceId
    val type: SerialDeviceType
    val name: String
}