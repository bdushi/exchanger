package al.bruno.exchanger.navigation

import al.bruno.exchanger.ui.ExchangeScaffold
import al.bruno.exchanger.ui.converter.ui.NewExchangeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    navController: NavHostController,
    navigationScreenActions: NavigationScreenActions
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Exchange
    ) {
        composable<NavigationScreen.Exchange> {
            ExchangeScaffold(
                navigationScreenActions = navigationScreenActions
            )
        }
        composable<NavigationScreen.NewExchange> {
            NewExchangeScreen(
                navigateUp = navigationScreenActions.navigateUp,
            )
        }
    }
}