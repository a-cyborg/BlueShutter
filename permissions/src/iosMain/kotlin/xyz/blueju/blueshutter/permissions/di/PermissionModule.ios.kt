package xyz.blueju.blueshutter.permissions.di

import org.koin.core.module.Module
import org.koin.dsl.module
import xyz.blueju.blueshutter.permissions.PermissionService
import xyz.blueju.blueshutter.permissions.PermissionServiceImpl

actual fun platformModule(): Module = module {
    factory<PermissionService> { PermissionServiceImpl() }
}
