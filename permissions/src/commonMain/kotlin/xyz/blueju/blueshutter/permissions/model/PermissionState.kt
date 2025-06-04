package xyz.blueju.blueshutter.permissions.model

enum class PermissionState {
    NEVER_ASKED,

    GRANTED,

    /**
     * Permission has been denied, but the app can request it again.
     */
    DENIED,

    /**
     * Permission has been denied permanently, and the app cannot request it again.
     * The user must manually enable it through settings.
     */
    SETTINGS_REQUIRED,
}
