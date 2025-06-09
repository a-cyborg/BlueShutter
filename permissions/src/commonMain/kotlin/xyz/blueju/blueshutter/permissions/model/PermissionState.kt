package xyz.blueju.blueshutter.permissions.model

enum class PermissionState(val priority: Int) {
    GRANTED(0),

    NEVER_ASKED(1),

    /**
     * Permission has been denied, but the app can request it again.
     */
    DENIED(2),

    /**
     * Permission has been denied permanently, and the app cannot request it again.
     * The user must manually enable it through settings.
     */
    SETTINGS_REQUIRED(3),
}
