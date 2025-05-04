package com.dd2d.presentation.schedule._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.BottomBarItem
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.fadeScreen
import com.dd2d.presentation.schedule.screen.ScheduleScreen
import kotlinx.serialization.Serializable

@Serializable
data object ScheduleScreenRoute : ScreenRoute, BottomBarItem {
    override val route: ScreenRoute = this
    override val labelRes: Int = com.dd2d.core.presentation.R.string.tab_main
    override val iconRes: Int = com.dd2d.core.presentation.R.drawable.tab_main

    override fun navigate(navController: NavController) {
        navController.toScheduleScreen()
    }
}

fun NavGraphBuilder.routeScheduleScreen(
    onScheduleClick: (codePostId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    fadeScreen<ScheduleScreenRoute> {
        ScheduleScreen(
            onScheduleClick = onScheduleClick,
            modifier = modifier,
        )
    }
}

fun NavController.toScheduleScreen() {
    navigate(ScheduleScreenRoute) {
        launchSingleTop = true
    }
}