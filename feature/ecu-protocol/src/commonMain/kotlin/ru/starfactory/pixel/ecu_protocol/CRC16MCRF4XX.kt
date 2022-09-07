package ru.starfactory.pixel.ecu_protocol

@Suppress("MagicNumber")
internal object CRC16MCRF4XX {
    fun update(crc: Short, data: Byte): Short {
        // TODO на это издевательство котлина над бинарной арифметикой нельзя смотреть без слез, просто закройте глаза
        var tmp = (data.toInt() xor (crc.toInt() and 0xff)) and 0xFF
        tmp = (tmp xor (tmp shl 4)) and 0xFF
        return (((tmp shl 8) or ((crc.toInt() shr 8) and 0xFF) xor (tmp shr 4) xor (tmp shl 3)) and 0xFFFF).toShort()
    }
}
