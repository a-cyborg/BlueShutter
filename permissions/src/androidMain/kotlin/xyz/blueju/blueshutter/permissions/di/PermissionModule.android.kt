package xyz.blueju.blueshutter.permissions.di

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import xyz.blueju.blueshutter.permissions.PermissionService
import xyz.blueju.blueshutter.permissions.PermissionServiceImpl
import xyz.blueju.blueshutter.permissions.handler.AndroidPermissionHandler
import xyz.blueju.blueshutter.permissions.handler.BluetoothPermissionHandler
import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory
import xyz.blueju.blueshutter.permissions.model.Permission

actual fun platformModule(): Module = module {
    factory<PermissionService> {
        PermissionServiceImpl(get())
    }

    factory<PermissionHandlerFactory> {
        PermissionHandlerFactory()
    }

    factory<AndroidPermissionHandler>(named(Permission.BLUETOOTH.name)) {
        BluetoothPermissionHandler(get(), inject())
    }
}
