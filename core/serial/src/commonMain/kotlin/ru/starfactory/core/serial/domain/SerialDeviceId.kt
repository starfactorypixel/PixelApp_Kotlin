package ru.starfactory.core.serial.domain

@JvmInline
value class SerialDeviceId internal constructor(val rawId: String) {
    constructor(type: SerialDeviceType, id: String) : this("${type.name}://$id")
}
