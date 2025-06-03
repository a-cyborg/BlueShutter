package xyz.blueju.blueshutter.feature.role_selection

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class RoleSelectionViewModel :
    ViewModel(),
    KoinComponent {
    init {
        println("Initialized RoleSelectionViewModel")
    }
}
