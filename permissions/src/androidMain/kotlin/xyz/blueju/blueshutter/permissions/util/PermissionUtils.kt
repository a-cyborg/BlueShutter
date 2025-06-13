package xyz.blueju.blueshutter.permissions.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
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

/**
 * Requests the specified permissions.
 *
 * @param permissions A list of permission strings to request.
 * @param onError A callback invoked with a [Throwable] if the permission request fails.
 */
internal fun Activity.requestPermissions(
    permissions: List<String>,
    onError: (Throwable) -> Unit
) {
    try {
        ActivityCompat.requestPermissions(
            this,
            permissions.toTypedArray(),
            333
        )
    } catch (t: Throwable) {
        onError(t)
    }
}

/**
 * Opens the app's settings screen.
 *
 * @param onError A callback invoked with a [Throwable] if the permission request fails.
 */
internal fun Context.navigateToSettings(
    onError: (Throwable) -> Unit,
) {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            setData("package:$packageName".toUri())
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(intent)
    } catch (t: Throwable) {
        onError(t)
    }
}
