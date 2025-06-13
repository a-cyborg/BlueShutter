package xyz.blueju.blueshutter.permissions.util

import android.content.Context
import androidx.core.content.edit

private const val PERMISSION_PREFS = "permission_prefs"

internal fun Context.hasRequestedPermissionBefore(permission: String): Boolean =
    getPermissionPrefs()
        .getBoolean(
            getPrefsKey(permission),
            false
        )

internal fun Context.markPermissionRequested(permission: String) {
    getPermissionPrefs()
        .edit {
            putBoolean(
                getPrefsKey(permission),
                true
            )
        }
}

private fun Context.getPermissionPrefs() =
    getSharedPreferences(
        PERMISSION_PREFS,
        Context.MODE_PRIVATE
    )

private fun getPrefsKey(permission: String): String =
    "requested_$permission"
