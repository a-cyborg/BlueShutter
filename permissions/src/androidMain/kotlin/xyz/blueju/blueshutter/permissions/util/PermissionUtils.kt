package xyz.blueju.blueshutter.permissions.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import xyz.blueju.blueshutter.permissions.model.PermissionState

/**
 * Returns a single [PermissionState] with the highest priority among all permissions.
 * For example, if any permission is denied, it returns DENIED even if others are granted.
 *
 * Priority: SETTINGS_REQUIRED > DENIED > NEVER_ASKED > GRANTED
 *
 * @return [PermissionState]
 */
internal fun checkPermissions(
    context: Context,
    activity: Lazy<Activity>,
    permissions: List<String>,
): PermissionState = permissions
    .map {
        if (!context.hasRequestedPermissionBefore(it)) {
            PermissionState.NEVER_ASKED
        } else if (context.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED) {
            PermissionState.GRANTED
        } else if (activity.value.shouldShowRequestPermissionRationale(it)) {
            PermissionState.DENIED
        } else {
            PermissionState.SETTINGS_REQUIRED
        }
    }.maxBy { it.priority }
