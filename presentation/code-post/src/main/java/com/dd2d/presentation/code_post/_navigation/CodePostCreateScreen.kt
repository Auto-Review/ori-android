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
import com.dd2d.presentation.code_post.screen.CodePostCreateScreen
import kotlinx.serialization.Serializable


@Serializable
object CodePostCreate

fun NavGraphBuilder.codePostCreateScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = slideInHorizontally { it },
    exit: ExitTransition = slideOutHorizontally { it },
    popEnter: EnterTransition =  enter,
    popExit: ExitTransition = exit
) {
    composable<CodePostCreate>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        CodePostCreateScreen(
            onBack = navController::safePopBackStack,
            modifier = modifier
        )
    }
}

fun NavController.toCodePostCreate() {
    navigate(CodePostCreate) {
        launchSingleTop = true
    }
}