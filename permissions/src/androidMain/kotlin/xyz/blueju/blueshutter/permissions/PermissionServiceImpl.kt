package xyz.blueju.blueshutter.permissions

import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory
import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

internal class PermissionServiceImpl(
    private val factory: PermissionHandlerFactory,
) : PermissionService {

    override fun checkPermission(permission: Permission): Result<PermissionState> =
        factory.invoke(permission).value.getPermissionsState()

    override suspend fun requestPermission(permission: Permission) {
        factory.invoke(permission).value.performPermissionRequest()
    }

    override fun openSettings(permission: Permission) {
        factory.invoke(permission).value.navigateToSettings()
    }
}
