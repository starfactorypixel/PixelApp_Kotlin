package ru.starfactory.pixel.ecu_connection.domain.source

data class Source(
    val sourceType: SourceType,
    val id: String,
    val name: String
)
