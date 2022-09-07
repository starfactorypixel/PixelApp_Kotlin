package ru.starfactory.pixel.ecu_protocol


data class EcuMessage(
    val transport: Transport,
    val haveAdditionalData: Boolean,
    val type: Type,
    val id: Int,
    val data: ByteArray,
) {
    enum class Type(val raw: Int) {
        MIRROR(0x00),
        BUFFER(0x01),
        CAN(0x02),
        ERROR(0x1F),
        ;

        companion object
    }

    enum class Transport(val raw: Int) {
        Raw(0x0),
        RS485(0x1),
        Bluetooth(0x2),
        ;

        companion object
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EcuMessage

        if (transport != other.transport) return false
        if (haveAdditionalData != other.haveAdditionalData) return false
        if (type != other.type) return false
        if (id != other.id) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = transport.hashCode()
        result = 31 * result + haveAdditionalData.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + id
        result = 31 * result + data.contentHashCode()
        return result
    }
}