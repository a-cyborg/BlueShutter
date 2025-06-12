package xyz.blueju.blueshutter.permissions

import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

internal class PermissionServiceImpl : PermissionService {
    override fun checkPermission(permission: Permission): Result<PermissionState> = Result.failure(NotImplementedError())

    override suspend fun requestPermission(permission: Permission) {}

    override fun openSettings(permission: Permission) {}
}
