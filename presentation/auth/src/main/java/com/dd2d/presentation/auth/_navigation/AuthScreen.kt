package com.dd2d.presentation.auth._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.core.presentation.navigation.navigateWithClearBackStack
import com.dd2d.presentation.auth.screen.AuthScreen
import kotlinx.serialization.Serializable

@Serializable object AuthScreen

fun NavGraphBuilder.authScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    popEnter: EnterTransition = EnterTransition.None,
    popExit: ExitTransition = fadeOut(),
    onAuthSuccess: () -> Unit
) {
    composable<AuthScreen>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        AuthScreen(
            onAuthSuccess = onAuthSuccess,
            modifier = modifier
        )
    }
}

fun NavController.toAuthScreen() {
    navigateWithClearBackStack(route = AuthScreen, launchSingleTop = true)
}