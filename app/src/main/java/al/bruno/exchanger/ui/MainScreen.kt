package al.bruno.exchanger.ui

import al.bruno.exchanger.navigation.NavigationGraph
import al.bruno.exchanger.navigation.NavigationScreenActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigationScreenActions = remember(navController) { NavigationScreenActions(navController) }
    NavigationGraph(
        navController,
        navigationScreenActions,
    )
}
