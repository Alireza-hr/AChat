package com.alirezahr.navigation

import androidx.navigation.NavOptions

sealed class ComposeNavigationOrders : NavigationOrders() {
    data class NavigateToRoute(val route: String, val options: NavOptions? = null) :
        ComposeNavigationOrders()

    data class NavigateUpWithResult<T>(
        val key: String, val result: T, val route: String? = null
    ) : ComposeNavigationOrders()

    data class PopUpToRoute(val route: String, val inclusive: Boolean) : ComposeNavigationOrders()
}

sealed class NavigationOrders {
    object NavigateUp : NavigationOrders()
}


