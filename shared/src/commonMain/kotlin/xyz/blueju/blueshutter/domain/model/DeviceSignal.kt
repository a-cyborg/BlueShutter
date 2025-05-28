package xyz.blueju.blueshutter.domain.model

data class DeviceSignal(
    val deviceId: String,
    val rssi: Int,
    val timestamp: Long,
)