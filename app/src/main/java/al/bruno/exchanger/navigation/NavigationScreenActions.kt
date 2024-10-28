package al.bruno.exchanger.navigation

import androidx.navigation.NavHostController

class NavigationScreenActions(navController: NavHostController) {
    val newExchange: () -> Unit = {
        navController.navigate(NavigationScreen.NewExchange) {
            launchSingleTop = true
        }
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}