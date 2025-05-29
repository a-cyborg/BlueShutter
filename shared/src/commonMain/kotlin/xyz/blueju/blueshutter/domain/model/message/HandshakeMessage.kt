package xyz.blueju.blueshutter.domain.model.message

import xyz.blueju.blueshutter.domain.model.command.Command
import xyz.blueju.blueshutter.domain.model.device.Device

sealed interface HandshakeMessage {
    data class HelloFromCentral(
        val centralDevice: Device,
        val supportedCommands: Set<Command>
    ) : HandshakeMessage

    data class AckFromPeripheral(
        val peripheralDevice: Device,
        val allowedCommands: Set<Command>
    ) : HandshakeMessage
}
