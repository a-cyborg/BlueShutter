package xyz.blueju.blueshutter.permissions.handler

import xyz.blueju.blueshutter.permissions.model.PermissionState

interface AndroidPermissionHandler {
    fun getPermissionsState(): Result<PermissionState>
    suspend fun performPermissionRequest()
    fun navigateToSettings()
}
