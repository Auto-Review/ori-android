package com.dd2d.presentation.my._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.presentation.my.screen.MyPageScreen
import kotlinx.serialization.Serializable

@Serializable
object MyPageScreen

sealed interface MyPageNavigator {
    data object CodePostCreate : MyPageNavigator
    data object TILPostCreate : MyPageNavigator
    class CodePost(val id: Int) : MyPageNavigator
    class TIL(val id: Int) : MyPageNavigator
}

fun NavGraphBuilder.myPageScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = slideInHorizontally { it },
    exit: ExitTransition = slideOutHorizontally { it },
    popEnter: EnterTransition =  enter,
    popExit: ExitTransition = exit,
    navigationEvent: (MyPageNavigator) -> Unit
) {
    composable<MyPageScreen>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        MyPageScreen(
            navigationEvent = navigationEvent,
            modifier = modifier
        )
    }
}

fun NavController.toMyPage() {
    navigate(MyPageScreen) {
        launchSingleTop = true
    }
}