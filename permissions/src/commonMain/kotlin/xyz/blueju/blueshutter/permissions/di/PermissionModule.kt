package xyz.blueju.blueshutter.permissions.di

import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun platformModule(): Module

val permissionModule = module {
    includes(platformModule())
}
