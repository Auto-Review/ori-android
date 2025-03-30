package com.dd2d.presentation.auth._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.auth.screen.AuthScreen
import kotlinx.serialization.Serializable

@Serializable
data object AuthScreenRoute : ScreenRoute

fun NavGraphBuilder.routeAuthScreen(
    onAuthSuccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
    horizontalScreen<AuthScreenRoute> {
        AuthScreen(
            onAuthSuccess = onAuthSuccess,
            modifier = modifier,
        )
    }
}

fun NavController.toAuthScreen() {
    navigate(AuthScreenRoute) {
        launchSingleTop = true
    }
}