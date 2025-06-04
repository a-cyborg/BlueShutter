package xyz.blueju.blueshutter.permissions.handler

import xyz.blueju.blueshutter.permissions.model.Permission

class PermissionHandlerFactory {
    fun create(permission: Permission): AndroidPermissionHandler = when (permission) {
        Permission.BLUETOOTH -> BluetoothPermissionHandler()
        Permission.CAMERA -> CameraPermissionHandler()
    }
}
