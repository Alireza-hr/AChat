package com.alirezahr.navigation

import androidx.navigation.NavOptionsBuilder
import javax.inject.Inject

class AChatComposeNavigator @Inject constructor(): AppComposeNavigator(){
    override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
        TODO("Not yet implemented")
    }

    override fun <T> navigateBackWithResult(key: String, result: T, route: String?) {
        TODO("Not yet implemented")
    }

    override fun popUpTo(route: String, inclusive: Boolean) {
        TODO("Not yet implemented")
    }

    override fun navigateAndClearBackStack(route: String) {
        TODO("Not yet implemented")
    }
}