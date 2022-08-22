package ru.starfactory.pixel.ecu_connection.domain.connection

data class EcuPrimaryState(
    val speed: Int, // km/h
    val batteryCharge: Float, // %
)
