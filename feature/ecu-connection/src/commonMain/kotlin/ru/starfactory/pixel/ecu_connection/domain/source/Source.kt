package ru.starfactory.pixel.ecu_connection.domain.source

sealed class Source {
    abstract val sourceType: SourceType

    object Demo : Source() {
        override val sourceType: SourceType = SourceType.DEMO
    }
    data class Serial(override val sourceType: SourceType, val id: String, val name: String) : Source()
}
