package xyz.blueju.blueshutter.permissions

import org.koin.java.KoinJavaComponent.get
import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory
import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState

actual class PermissionService {
    private val handlerFactory: PermissionHandlerFactory by lazy {
        get(PermissionHandlerFactory::class.java)
    }

    actual fun checkPermission(permission: Permission): Result<PermissionState> {
        return handlerFactory.create(permission).getPermissionsState()
    }


    actual suspend fun requestPermission(permission: Permission) {
        handlerFactory.create(permission).performPermissionRequest()
    }

    actual fun openSettings(permission: Permission) {
        handlerFactory.create(permission).navigateToSettings()
    }
}
