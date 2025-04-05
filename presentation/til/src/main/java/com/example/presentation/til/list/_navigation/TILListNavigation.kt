package com.example.presentation.til.list._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.BottomBarItem
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.fadeScreen
import com.example.presentation.til.list.screen.TILListScreen
import kotlinx.serialization.Serializable

@Serializable
data object TILListScreenRoute : ScreenRoute, BottomBarItem {
    override val route: ScreenRoute = this
    override val labelRes: Int = com.dd2d.core.presentation.R.string.tab_til
    override val iconRes: Int = com.dd2d.core.presentation.R.drawable.tab_til

    override fun navigate(navController: NavController) {
        navController.toTILListScreen()
    }
}

fun NavGraphBuilder.routeTILListScreen(
    modifier: Modifier = Modifier,
) {
    fadeScreen<TILListScreenRoute> {
        TILListScreen(
            modifier = modifier,
        )
    }
}

fun NavController.toTILListScreen() {
    navigate(TILListScreenRoute) {
        launchSingleTop = true
    }
}