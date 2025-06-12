package xyz.blueju.blueshutter.permissions.handler

import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import xyz.blueju.blueshutter.permissions.model.Permission
import kotlin.test.assertEquals

class PermissionHandlerFactoryTest {

    private val mockBluetoothHandler: BluetoothPermissionHandler = mockk()
    private val mockCameraHandler: CameraPermissionHandler = mockk()

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    factory<AndroidPermissionHandler>(named(Permission.BLUETOOTH.name)) {
                        mockBluetoothHandler
                    }

                    factory<AndroidPermissionHandler>(named(Permission.CAMERA.name)) {
                        mockCameraHandler
                    }
                },
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        clearAllMocks()
    }

    @Test
    fun invokeWithBluetoothPermissionReturnsBluetoothPermissionHandler() {
        // Given
        val factory = PermissionHandlerFactory()

        // When
        val actual = factory.invoke(Permission.BLUETOOTH).value

        // Then
        assertEquals(mockBluetoothHandler, actual)
    }

    @Test
    fun invokeWithCameraPermissionReturnsCameraPermissionHandler() {
        // Given
        val factory = PermissionHandlerFactory()

        // When
        val actual = factory.invoke(Permission.CAMERA).value

        // Then
        assertEquals(mockCameraHandler, actual)
    }
}
