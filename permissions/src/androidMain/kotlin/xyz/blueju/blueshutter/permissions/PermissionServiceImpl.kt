package xyz.blueju.blueshutter.permissions

import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory
import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

internal class PermissionServiceImpl(private val factory: PermissionHandlerFactory) :
    PermissionService {

    override fun checkPermission(permission: Permission): Result<PermissionState> =
        factory.create(permission).getPermissionsState()

    override suspend fun requestPermission(permission: Permission) {
        factory.create(permission).performPermissionRequest()
    }

    override fun openSettings(permission: Permission) {
        factory.create(permission).navigateToSettings()
    }
}
