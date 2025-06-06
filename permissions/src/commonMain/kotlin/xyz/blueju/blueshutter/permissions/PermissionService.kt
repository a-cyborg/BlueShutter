package xyz.blueju.blueshutter.permissions

import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

expect interface PermissionService {
    fun checkPermission(permission: Permission): Result<PermissionState>
    suspend fun requestPermission(permission: Permission)
    fun openSettings(permission: Permission)
}
