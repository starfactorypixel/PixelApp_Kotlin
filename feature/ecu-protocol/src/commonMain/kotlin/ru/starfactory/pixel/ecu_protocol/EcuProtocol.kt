package ru.starfactory.pixel.ecu_protocol

interface EcuProtocol {
    fun writeHandshake()
    fun subscribe(id: Int)
    fun unsubscribe(id: Int)
    fun writeMessage(message: EcuMessage)

    companion object {
        operator fun invoke(sender: (EcuMessage) -> Unit): EcuProtocol = EcuProtocolImpl(sender)
    }
}

internal class EcuProtocolImpl(
    private val sender: (EcuMessage) -> Unit
) : EcuProtocol {
    override fun writeHandshake() {
        val msg = EcuMessage(
            type = EcuMessage.Type.HANDSHAKE,
            id = 0
        )
        writeMessage(msg)
    }

    override fun subscribe(id: Int) {
        val msg = EcuMessage(
            type = EcuMessage.Type.SUBSCRIPTIONS,
            id = id
        )
        writeMessage(msg)
    }

    override fun unsubscribe(id: Int) {
        val msg = EcuMessage(
            type = EcuMessage.Type.SUBSCRIPTIONS,
            id = id or UNSUBSCRIBE_MASK
        )
        writeMessage(msg)
    }

    override fun writeMessage(message: EcuMessage) {
        sender(message)
    }

    companion object {
        const val UNSUBSCRIBE_MASK = 0x8000
    }
}
