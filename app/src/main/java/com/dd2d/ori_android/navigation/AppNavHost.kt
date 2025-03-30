package com.dd2d.ori_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.navigation.safePopBackStack
import com.dd2d.ori_android.navigation.main.mainScreen
import com.dd2d.ori_android.navigation.main.toMain
import com.dd2d.presentation.auth._navigation.authScreen
import com.dd2d.presentation.code_post.create._navigation.codePostCreateScreen
import com.dd2d.presentation.code_post.detail._navigation.codePostScreen

@Composable
internal fun AppNavHost(
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        authScreen(onAuthSuccess = navController::toMain)

        mainScreen(appNavController = navController)

        codePostScreen(onBack = navController::safePopBackStack)
        codePostCreateScreen(onBack = navController::safePopBackStack)
    }
}