package ru.starfactory.pixel.ecu_protocol

@Suppress("MagicNumber")
data class EcuMessage(
    val type: Type,
    val id: Int,
    val data: ByteArray = ByteArray(0),
) {
    enum class Type(val raw: Int) {
        MIRROR(0x00),
        BUFFER(0x01),
        CAN(0x02),

        HANDSHAKE(0x10),
        SUBSCRIPTIONS(0x11),

        EVENT(0x15),

        ERROR(0x1E),
        ;

        companion object
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EcuMessage

        if (type != other.type) return false
        if (id != other.id) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + id
        result = 31 * result + data.contentHashCode()
        return result
    }
}
