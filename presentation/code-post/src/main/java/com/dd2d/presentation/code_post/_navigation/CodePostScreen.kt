package com.dd2d.presentation.code_post._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.core.presentation.navigation.safePopBackStack
import com.dd2d.presentation.code_post.screen.CodePostScreen
import kotlinx.serialization.Serializable

@Serializable
class CodePost(val id: Int)

fun NavGraphBuilder.codePostScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = slideInHorizontally { it },
    exit: ExitTransition = slideOutHorizontally { it },
    popEnter: EnterTransition = enter,
    popExit: ExitTransition = exit,
) {
    composable<CodePost>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        CodePostScreen(
            onBack = navController::safePopBackStack,
            modifier = modifier
        )
    }
}

fun NavController.toCodePost(id: Int, singleTop: Boolean = true) {
    navigate(CodePost(id = id)) {
        launchSingleTop = singleTop
    }
}
