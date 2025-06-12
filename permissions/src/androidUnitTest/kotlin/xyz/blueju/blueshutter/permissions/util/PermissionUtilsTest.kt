package xyz.blueju.blueshutter.permissions.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState
import kotlin.test.assertEquals

class PermissionUtilsTest {

    private var context: Context = mockk(relaxed = true)
    private val activity: Lazy<Activity> = lazy { mockk(relaxed = true) }
    private val permissionBluetooth = Manifest.permission.BLUETOOTH

    @Test
    fun returnsNEVER_ASKED_IfPermissionNotRequestedBefore() {
        // Given
        every { context.hasRequestedPermissionBefore(Permission.BLUETOOTH.name) } returns false

        // When
        val actual = checkPermissions(context, activity, listOf(permissionBluetooth))

        // Then
        assertEquals(PermissionState.NEVER_ASKED, actual)
    }

    @Test
    fun returnsGRANTED_IfPermissionRequestedBefore_AndCheckSelfPermissionIsGranted() {
        // Given
        every { context.hasRequestedPermissionBefore(permissionBluetooth) } returns true

        every { context.checkSelfPermission(any()) } returns PackageManager.PERMISSION_GRANTED

        // When
        val actual = checkPermissions(context, activity, listOf(permissionBluetooth))

        // Then
        assertEquals(PermissionState.GRANTED, actual)
    }

    @Test
    fun returnsDENIED_IfPermissionRequestedBefore_AndCheckSelfPermissionIsNotGranted() {
        // Given
        every { context.hasRequestedPermissionBefore(permissionBluetooth) } returns true

        every { context.checkSelfPermission(any()) } returns PackageManager.PERMISSION_DENIED

        every { activity.value.shouldShowRequestPermissionRationale(any()) } returns true

        // When
        val actual = checkPermissions(context, activity, listOf(permissionBluetooth))

        // Then
        assertEquals(PermissionState.DENIED, actual)
    }

    @Test
    fun returnSETTINGS_REQUIRED_IfPermissionRequestedBefore_AndCheckSelfPermissionIsNotGranted_AndShouldShowRationaleIsFalse() {
        // Given
        every { context.hasRequestedPermissionBefore(permissionBluetooth) } returns true

        every { context.checkSelfPermission(any()) } returns PackageManager.PERMISSION_DENIED

        every { activity.value.shouldShowRequestPermissionRationale(any()) } returns false

        // When
        val actual = checkPermissions(context, activity, listOf(permissionBluetooth))

        // Then
        assertEquals(PermissionState.SETTINGS_REQUIRED, actual)
    }
}
