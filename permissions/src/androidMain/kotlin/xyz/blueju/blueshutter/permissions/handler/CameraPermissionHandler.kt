package xyz.blueju.blueshutter.permissions.handler

import xyz.blueju.blueshutter.permissions.model.PermissionState

class CameraPermissionHandler : AndroidPermissionHandler {
    override fun getPermissionsState(): Result<PermissionState> =
        Result.failure(NotImplementedError())

    override suspend fun performPermissionRequest() {}

    override fun navigateToSettings() {}
}
