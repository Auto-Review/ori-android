package com.dd2d.presentation.schedule._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.presentation.schedule.screen.ScheduleScreen
import kotlinx.serialization.Serializable

@Serializable
object ScheduleScreen

fun NavGraphBuilder.scheduleScreen(
    navController: NavController,
    onCodePostCreateClick: () -> Unit,
    onTILPostCreateClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
    enter: EnterTransition = slideInHorizontally { it },
    exit: ExitTransition = slideOutHorizontally { it },
    popEnter: EnterTransition =  enter,
    popExit: ExitTransition = exit
) {
    composable<ScheduleScreen>(
        enterTransition = { enter },
        exitTransition = { exit },
        popEnterTransition = { popEnter },
        popExitTransition = { popExit }
    ) {
        ScheduleScreen(
            onCodePostCreateClick = onCodePostCreateClick,
            onTILPostCreateClick = onTILPostCreateClick,
            modifier = modifier
        )
    }
}

fun NavController.toScheduleScreen() {
    navigate(ScheduleScreen) {
        launchSingleTop = true
    }
}