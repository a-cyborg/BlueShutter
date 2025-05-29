package xyz.blueju.blueshutter.domain.model.message

sealed interface Payload {
    data object EmptyPayload : Payload

    data class RecordingStatus(
        val started: Boolean,
        val timestamp: Long,
    ) : Payload

    data class TimerPayload(
        val timerSeconds: Int,
    ) : Payload
}
