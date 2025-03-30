package com.dd2d.presentation.my._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.BottomBarItem
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.fadeScreen
import com.dd2d.presentation.my.model.MyPageNavigateEvent
import com.dd2d.presentation.my.screen.MyPageScreen
import kotlinx.serialization.Serializable

@Serializable
data object MyPageScreenRoute : ScreenRoute, BottomBarItem {
    override val route: ScreenRoute = this
    override val labelRes: Int = com.dd2d.core.presentation.R.string.tab_my
    override val iconRes: Int = com.dd2d.core.presentation.R.drawable.tab_my

    override fun navigate(navController: NavController) {
        navController.toMyPageScreen()
    }
}

fun NavGraphBuilder.routeMyPageScreen(
    navigationEvent: (MyPageNavigateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    fadeScreen<MyPageScreenRoute> {
        MyPageScreen(
            navigationEvent = navigationEvent,
            modifier = modifier,
        )
    }
}

fun NavController.toMyPageScreen() {
    navigate(MyPageScreenRoute) {
        launchSingleTop = true
    }
}