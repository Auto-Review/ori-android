package com.dd2d.core.presentation.navigation

import androidx.navigation.NavController

fun NavController.safePopBackStack() {
    if(previousBackStackEntry != null) {
        popBackStack()
    }
}

fun <T: Any> NavController.navigateWithClearBackStack(route: T, launchSingleTop: Boolean = true) {
    navigate(route) {
        this.launchSingleTop = launchSingleTop
        popUpTo(this@navigateWithClearBackStack.graph.id) {
            inclusive = true
        }
    }
}