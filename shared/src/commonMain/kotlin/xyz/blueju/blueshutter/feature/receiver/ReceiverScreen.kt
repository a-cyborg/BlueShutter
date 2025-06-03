package xyz.blueju.blueshutter.feature.receiver

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import xyz.blueju.blueshutter.feature.role_selection.RoleSelectionViewModel

@Composable
fun ReceiverScreen(
    viewModel: RoleSelectionViewModel = koinViewModel<RoleSelectionViewModel>(),
) {
    Text("ReceiverScreen")
}
