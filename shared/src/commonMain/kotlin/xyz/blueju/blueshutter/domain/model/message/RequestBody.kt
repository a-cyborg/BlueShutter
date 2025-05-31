package xyz.blueju.blueshutter.domain.model.message

import xyz.blueju.blueshutter.domain.model.command.Command
import xyz.blueju.blueshutter.domain.model.command.Command.START_RECORDING
import xyz.blueju.blueshutter.domain.model.command.Command.STOP_RECORDING
import xyz.blueju.blueshutter.domain.model.command.Command.TAKE_PHOTO
import xyz.blueju.blueshutter.domain.model.message.Payload.EmptyPayload
import xyz.blueju.blueshutter.domain.model.message.Payload.TimerPayload
import xyz.blueju.blueshutter.domain.model.message.RequestBehavior.REQUIRE_ACK
import xyz.blueju.blueshutter.domain.model.message.RequestBehavior.REQUIRE_ACK_AND_RESPONSE
import xyz.blueju.blueshutter.domain.model.message.RequestBehavior.REQUIRE_RESPONSE

interface RequestBody<T : Payload> {
    val command: Command
    val behavior: RequestBehavior
    val payload: Payload
}

data object TakePhotoRequest : RequestBody<EmptyPayload> {
    override val command: Command = TAKE_PHOTO
    override val behavior: RequestBehavior = REQUIRE_ACK
    override val payload: Payload = EmptyPayload
}

data class TakePhotoWithDelayRequest(
    override val command: Command = TAKE_PHOTO,
    override val behavior: RequestBehavior = REQUIRE_RESPONSE,
    override val payload: TimerPayload,
) : RequestBody<TimerPayload>

data object StartRecordingRequest : RequestBody<EmptyPayload> {
    override val command: Command = START_RECORDING
    override val behavior: RequestBehavior = REQUIRE_ACK_AND_RESPONSE
    override val payload: Payload = EmptyPayload
}

data object StopRecordingRequest : RequestBody<EmptyPayload> {
    override val command: Command = STOP_RECORDING
    override val behavior: RequestBehavior = REQUIRE_ACK_AND_RESPONSE
    override val payload: Payload = EmptyPayload
}
