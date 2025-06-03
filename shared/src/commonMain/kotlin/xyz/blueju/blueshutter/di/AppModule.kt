package xyz.blueju.blueshutter.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import xyz.blueju.blueshutter.feature.role_selection.RoleSelectionViewModel

val appModule = module {
    viewModel<RoleSelectionViewModel> { RoleSelectionViewModel() }
}
