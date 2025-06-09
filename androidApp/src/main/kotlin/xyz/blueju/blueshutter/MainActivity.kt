package xyz.blueju.blueshutter

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(appDeclaration = {
                androidContext(applicationContext)
                modules(
                    module { single<Activity> { this@MainActivity } },
                )
            })
        }
    }
}
