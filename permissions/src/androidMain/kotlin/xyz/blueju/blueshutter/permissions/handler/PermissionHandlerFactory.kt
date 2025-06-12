package xyz.blueju.blueshutter.permissions.handler

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import xyz.blueju.blueshutter.permissions.model.Permission

class PermissionHandlerFactory : KoinComponent {
    operator fun invoke(permission: Permission): Lazy<AndroidPermissionHandler> = when (permission) {
        Permission.BLUETOOTH -> inject<AndroidPermissionHandler>(named(Permission.BLUETOOTH.name))
        Permission.CAMERA -> inject<AndroidPermissionHandler>(named(Permission.CAMERA.name))
    }
}
