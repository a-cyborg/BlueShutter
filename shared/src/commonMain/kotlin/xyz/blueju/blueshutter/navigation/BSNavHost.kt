package xyz.blueju.blueshutter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import xyz.blueju.blueshutter.feature.receiver.ReceiverScreen
import xyz.blueju.blueshutter.feature.role_selection.RoleSelectionScreen
import xyz.blueju.blueshutter.feature.role_selection.RoleSelectionViewModel
import xyz.blueju.blueshutter.feature.sender.SenderScreen

@Composable
fun BSNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destination.ROLE_SELECTION.route,
    ) {
        composable(Destination.ROLE_SELECTION.route) {
            RoleSelectionScreen(
                viewModel = koinViewModel<RoleSelectionViewModel>(),
            )
        }

        composable(Destination.SENDER.route) {
            SenderScreen()
        }

        composable(Destination.RECEIVER.route) {
            ReceiverScreen()
        }
    }
}
