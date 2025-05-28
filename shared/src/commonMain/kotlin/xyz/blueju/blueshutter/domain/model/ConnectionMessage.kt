package xyz.blueju.blueshutter.domain.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class ConnectionMessage {
    @Serializable
    data object ReadyToReceive : ConnectionMessage()

    @Serializable
    data object ReadyToSend : ConnectionMessage()

    @Serializable
    data class CommandRequest(val command: CommandCode) : ConnectionMessage()

    @Serializable
    data class Ack(val command: CommandCode) : ConnectionMessage()

    @Serializable
    data class Error(val message: String) : ConnectionMessage()
}