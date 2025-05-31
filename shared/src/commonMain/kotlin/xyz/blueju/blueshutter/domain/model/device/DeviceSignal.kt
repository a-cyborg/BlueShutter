package xyz.blueju.blueshutter.domain.model.device

data class DeviceSignal(
    val deviceId: String,
    val rssi: Int,
    val timestamp: Long,
)
