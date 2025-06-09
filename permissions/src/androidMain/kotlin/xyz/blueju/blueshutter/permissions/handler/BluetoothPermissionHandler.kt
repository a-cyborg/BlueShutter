package xyz.blueju.blueshutter.permissions.handler

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import xyz.blueju.blueshutter.permissions.model.PermissionState
import xyz.blueju.blueshutter.permissions.util.checkPermissions

class BluetoothPermissionHandler(
    private val context: Context,
    private val activity: Lazy<Activity>,
) : AndroidPermissionHandler {
    override fun getPermissionsState(): Result<PermissionState> {
        try {
            val state = checkPermissions(context, activity, bluetoothPermissions)
            return Result.success(state)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun performPermissionRequest() {}

    override fun navigateToSettings() {}

    private val bluetoothPermissions: List<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADVERTISE,
            )
        } else {
            listOf(
                Manifest.permission.BLUETOOTH,
            )
        }
}
