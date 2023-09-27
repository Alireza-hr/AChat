package com.alirezahr.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

abstract class Navigator {
    val navigationOrders = MutableSharedFlow<NavigationOrders>(extraBufferCapacity = Int.MAX_VALUE)

    //allow to to observe viewmodels on navigation
    val navControllerFlow = MutableStateFlow<NavController?>(null)

    fun navigateUp() {
        navigationOrders.tryEmit(NavigationOrders.NavigateUp)
    }
}

abstract class AppComposeNavigator : Navigator() {
    abstract fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
    abstract fun <T> navigateBackWithResult(key: String, result: T, route: String?)

    abstract fun popUpTo(route: String, inclusive: Boolean)
    abstract fun navigateAndClearBackStack(route: String)

    suspend fun handleNavigationOrders(navController: NavController) {
        navigationOrders
            .onSubscription { this@AppComposeNavigator.navControllerFlow.value = navController }
            .onCompletion { this@AppComposeNavigator.navControllerFlow.value = null }
            .collect { navController.handleComposeNavigationOrders(it) }
    }

    private fun NavController.handleComposeNavigationOrders(navigationOrders: NavigationOrders) {
        when (navigationOrders) {
            is ComposeNavigationOrders.NavigateToRoute -> {
                navigate(navigationOrders.route, navigationOrders.options)
            }

            NavigationOrders.NavigateUp -> navigateUp()
            is ComposeNavigationOrders.PopUpToRoute -> popBackStack(
                navigationOrders.route,
                navigationOrders.inclusive
            )

            is ComposeNavigationOrders.NavigateUpWithResult<*> -> {
                navUpWithResult(navigationOrders)
            }
        }
    }
    private fun NavController.navUpWithResult(
        navigationOrders: ComposeNavigationOrders.NavigateUpWithResult<*>
    ) {
        val backStackEntry =
            navigationOrders.route?.let { getBackStackEntry(it) }
                ?: previousBackStackEntry
        backStackEntry?.savedStateHandle?.set(
            navigationOrders.key,
            navigationOrders.result
        )

        navigationOrders.route?.let {
            popBackStack(it, false)
        } ?: run {
            navigateUp()
        }
    }
}