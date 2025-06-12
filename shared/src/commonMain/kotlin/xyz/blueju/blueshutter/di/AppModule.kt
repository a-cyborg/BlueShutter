package xyz.blueju.blueshutter.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import xyz.blueju.blueshutter.feature.role_selection.RoleSelectionViewModel
import xyz.blueju.blueshutter.permissions.di.permissionModule

val viewModelModule = module {
    viewModel<RoleSelectionViewModel> { RoleSelectionViewModel(get()) }
}

val appModule = module {
    includes(
        viewModelModule,
        permissionModule,
    )
}
