package com.dd2d.ori_android.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.ori_android.navigation.main.MainScreen
import com.dd2d.ori_android.navigation.main.mainScreen
import com.dd2d.presentation.code_post._navigation.CodePostList
import com.dd2d.presentation.code_post._navigation.codePostCreateScreen
import com.dd2d.presentation.code_post._navigation.codePostListScreen
import com.dd2d.presentation.code_post._navigation.codePostScreen

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen,
        modifier = modifier
    ) {
        mainScreen(appNavController = navController)
//        codePostListScreen(
//            navController = navController,
//            popEnter = EnterTransition.None,
//            popExit = ExitTransition.None
//        )
        codePostScreen(
            navController = navController,
        )
        codePostCreateScreen(navController)
    }
}