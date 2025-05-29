package xyz.blueju.blueshutter.domain.model.message

import kotlin.jvm.JvmInline

@JvmInline
value class SequenceId(val value: Int)

@JvmInline
value class RequestId(val value: String)

sealed interface BluetoothMessage {
    data class Request(
        val seqId: SequenceId,
        val requestId: RequestId,
        val body: RequestBody<*>,
    ) : BluetoothMessage

    data class Response(
        val requestId: RequestId,
        val result: Result<Payload>
    ) : BluetoothMessage

    data class Ack(
        val seqId: SequenceId,
        val requestId: RequestId
    ) : BluetoothMessage

    data class Nack(
        val seqId: SequenceId,
        val requestId: RequestId
    ) : BluetoothMessage

    data object KeepAlive : BluetoothMessage  // Ping

    data object KeepAliveAck : BluetoothMessage  // Pong

    data class Error(
        val errorCode: ErrorCode,
        val message: String,
    ) : BluetoothMessage
}