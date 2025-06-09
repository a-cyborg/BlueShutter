package xyz.blueju.blueshutter.permissions.handler

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named
import xyz.blueju.blueshutter.permissions.model.Permission

class PermissionHandlerFactory : KoinComponent {
    operator fun invoke(permission: Permission): AndroidPermissionHandler = when (permission) {
        Permission.BLUETOOTH -> get<AndroidPermissionHandler>(named(permission.name))
        Permission.CAMERA -> CameraPermissionHandler()
    }
}
