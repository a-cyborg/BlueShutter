package xyz.blueju.blueshutter.domain.model

/**
 * Represents bluetooth the role of the current device.
 *
 * - CENTRAL: initiates scanning and connects to peripherals. After connection, operates as the receiver.
 * - PERIPHERAL: advertises and waits for incoming connections. After connection, operates as the sender.
 */
enum class Role {
    CENTRAL,
    PERIPHERAL
}