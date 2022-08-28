package ru.starfactory.core.serial.domain

interface SerialDevice {
    val id: String
    val type: SerialDeviceType
    val name: String
}