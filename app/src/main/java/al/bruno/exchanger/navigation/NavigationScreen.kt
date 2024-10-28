package al.bruno.exchanger.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationScreen {
    @Serializable
    data object Exchange : NavigationScreen()
    @Serializable
    data object NewExchange : NavigationScreen()
}