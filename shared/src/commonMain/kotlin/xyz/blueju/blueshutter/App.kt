package xyz.blueju.blueshutter

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import xyz.blueju.blueshutter.di.appModule
import xyz.blueju.blueshutter.navigation.BSNavHost
import xyz.blueju.blueshutter.ui.theme.BlueShutterTheme

@Composable
fun App(appDeclaration: KoinAppDeclaration = {}) {
    KoinApplication(
        application = {
            appDeclaration.invoke(this)
            modules(appModule)
        },
    ) {
        BlueShutterTheme {
            Surface {
                BSNavHost()
            }
        }
    }
}
