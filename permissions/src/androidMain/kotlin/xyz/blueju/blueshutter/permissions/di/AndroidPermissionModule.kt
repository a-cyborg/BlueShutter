package xyz.blueju.blueshutter.permissions.di

import org.koin.dsl.module
import xyz.blueju.blueshutter.permissions.PermissionService
import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory

val androidPermissionModule = module {
    factory<PermissionService> { PermissionService() }
    factory<PermissionHandlerFactory> { PermissionHandlerFactory() }
}
