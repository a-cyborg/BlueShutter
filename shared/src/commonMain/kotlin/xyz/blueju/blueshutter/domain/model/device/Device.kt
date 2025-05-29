package xyz.blueju.blueshutter.domain.model.device

import kotlin.jvm.JvmInline

@JvmInline
value class DeviceId(val value: String)

data class Device(
    val id: DeviceId,
    val name: String,
    val role: Role,
)
