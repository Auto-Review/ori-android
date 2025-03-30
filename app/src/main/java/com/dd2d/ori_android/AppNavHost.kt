package com.dd2d.ori_android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.navigation.DefaultNavHost
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.safePopBackStack
import com.dd2d.ori_android.presentation_main._navigation.routeMainScreen
import com.dd2d.ori_android.presentation_main._navigation.toMainScreen
import com.dd2d.presentation.auth._navigation.routeAuthScreen
import com.dd2d.presentation.code_post.create._navigation.routeCodePostCreateScreen
import com.dd2d.presentation.code_post.detail._navigation.routeCodePostScreen
import com.example.presentation.til.create._navigation.routeTILCreateScreen

@Composable
internal fun AppNavHost(
    startDestination: ScreenRoute,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    DefaultNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        routeAuthScreen(onAuthSuccess = navController::toMainScreen)

        routeMainScreen(appNavController = navController)

        routeCodePostScreen(onBack = navController::safePopBackStack)
        routeCodePostCreateScreen(onBack = navController::safePopBackStack)

        routeTILCreateScreen(onBack = navController::safePopBackStack)
    }
}