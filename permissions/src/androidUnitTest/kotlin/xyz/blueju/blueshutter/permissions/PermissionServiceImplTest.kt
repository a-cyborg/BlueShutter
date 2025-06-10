package xyz.blueju.blueshutter.permissions

import io.mockk.CapturingSlot
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import xyz.blueju.blueshutter.permissions.handler.AndroidPermissionHandler
import xyz.blueju.blueshutter.permissions.handler.BluetoothPermissionHandler
import xyz.blueju.blueshutter.permissions.handler.PermissionHandlerFactory
import xyz.blueju.blueshutter.permissions.model.Permission
import xyz.blueju.blueshutter.permissions.model.PermissionState
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PermissionServiceImplTest {

    private val mockHandlerFactory: PermissionHandlerFactory = mockk()
    private val mockPermissionHandler: BluetoothPermissionHandler = mockk()

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    factory<PermissionHandlerFactory> {
                        mockHandlerFactory
                    }
                    factory<AndroidPermissionHandler>(named(Permission.BLUETOOTH.name)) {
                        mockPermissionHandler
                    }
                },
            )
        }

        every { mockHandlerFactory.invoke(Permission.BLUETOOTH) } returns
            mockPermissionHandler

        every { mockPermissionHandler.getPermissionsState() } returns
            Result.success(PermissionState.NEVER_ASKED)

        coEvery { mockPermissionHandler.performPermissionRequest() } answers {}

        every { mockPermissionHandler.navigateToSettings() } answers {}
    }

    @After
    fun tearDown() {
        stopKoin()
        clearAllMocks()
    }

    @Test
    fun checkPermissionTriggersHandlerGetPermissionsState() {
        // Given
        val service = PermissionServiceImpl(mockHandlerFactory)

        // When
        val result = service.checkPermission(Permission.BLUETOOTH)

        // Then
        verify { mockPermissionHandler.getPermissionsState() }
        assertEquals(result, Result.success(PermissionState.NEVER_ASKED))
    }

    @Test
    fun requestPermissionTriggersHandlerPerformPermissionRequest() = runTest {
        // Given
        val service = PermissionServiceImpl(mockHandlerFactory)

        // When
        service.requestPermission(Permission.BLUETOOTH)

        // Then
        coVerify { mockPermissionHandler.performPermissionRequest() }
    }

    @Test
    fun openSettingsTriggersHandlerNavigateToSettings() {
        // Given
        val service = PermissionServiceImpl(mockHandlerFactory)

        // When
        service.openSettings(Permission.BLUETOOTH)

        // Then
        verify { mockPermissionHandler.navigateToSettings() }
    }

    @Test
    fun serviceFunctionsTriggersFactoryInvokeWithPermission() {
        // Given
        val mockHandlerFactory: PermissionHandlerFactory = mockk()
        val mockHandler: AndroidPermissionHandler = mockk()

        val service = PermissionServiceImpl(mockHandlerFactory)
        val permission = Permission.CAMERA
        val captureSlot = CapturingSlot<Permission>()

        every { mockHandlerFactory.invoke(capture(captureSlot)) } returns
            mockHandler

        every { mockHandler.getPermissionsState() } returns
            Result.success(PermissionState.GRANTED)

        // When
        val result = service.checkPermission(permission)

        // Then
        verify { mockHandlerFactory::invoke.invoke(any()) }
        assertTrue { captureSlot.isCaptured }
        assertTrue { captureSlot.captured == permission }
        assertEquals(result, Result.success(PermissionState.GRANTED))
    }
}
