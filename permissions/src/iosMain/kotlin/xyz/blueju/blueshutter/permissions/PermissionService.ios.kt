package xyz.blueju.blueshutter.permissions

import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

actual class PermissionService {
    actual fun checkPermission(permission: Permission): Result<PermissionState> {
        return Result.failure(NotImplementedError())
    }

    actual suspend fun requestPermission(permission: Permission) {}

    actual fun openSettings(permission: Permission) {}
}
