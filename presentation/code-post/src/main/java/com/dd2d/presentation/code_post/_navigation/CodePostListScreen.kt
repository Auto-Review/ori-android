package com.dd2d.presentation.code_post._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.presentation.code_post.screen.CodePostListScreen
import kotlinx.serialization.Serializable

@Serializable
object CodePostList

fun NavGraphBuilder.codePostListScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    popEnter: EnterTransition = enter,
    popExit: ExitTransition = exit,
) {
    composable<CodePostList>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        CodePostListScreen(
            onCodePostClick = navController::toCodePost,
            modifier = modifier
        )
    }
}

fun NavController.toCodePostList() {
    navigate(CodePostList) {
        launchSingleTop = true
    }
}