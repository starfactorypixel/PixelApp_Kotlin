package ru.starfactory.pixel.ecu_protocol

interface EcuProtocol {
    fun writeHandshake()

    companion object {
        operator fun invoke(sender: (EcuMessage) -> Unit): EcuProtocol = EcuProtocolImpl(sender)
    }
}

internal class EcuProtocolImpl(
    private val sender: (EcuMessage) -> Unit
) : EcuProtocol {
//    override fun readUIntRegister(registerId: Int): UInt {
//        val raw = readRegister(registerId)
//        check(raw.remaining() == INT_SIZE) { "Wrong data size ${raw.remaining()}" }
//        return raw.int.toUInt()
//    }
//
//    override fun readUByteRegister(registerId: Int): UByte {
//        val raw = readRegister(registerId)
//        check(raw.remaining() == BYTE_SIZE) { "Wrong data size ${raw.remaining()}" }
//        return raw.get().toUByte()
//    }
//
//    private fun readRegister(registerId: Int): ByteBuffer {
//        val request = EcuMessage(
//            type = EcuMessage.Type.BUFFER,
//            id = registerId
//        )
//        raw.writeMessage(request)
//        return ByteBuffer.wrap(raw.readMessage().data)
//    }

    override fun writeHandshake() {
        val msg = EcuMessage(
            type = EcuMessage.Type.HANDSHAKE,
            id = 0
        )
        sender(msg)
    }

    companion object {
//        private const val BYTE_SIZE = 1
//        private const val INT_SIZE = 4
    }
}
