package com.dd2d.presentation.code_post.list._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.BottomBarItem
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.fadeScreen
import com.dd2d.presentation.code_post.list.screen.CodePostListScreen
import kotlinx.serialization.Serializable

@Serializable
data object CodePostListScreenRoute : ScreenRoute, BottomBarItem {
    override val route: ScreenRoute = this
    override val labelRes: Int = com.dd2d.core.presentation.R.string.tab_code
    override val iconRes: Int = com.dd2d.core.presentation.R.drawable.tab_code

    override fun navigate(navController: NavController) {
        navController.toCodePostListScreen()
    }
}

fun NavGraphBuilder.routeCodePostListScreen(
    onDetailClick: (id: Int) -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    fadeScreen<CodePostListScreenRoute> {
        CodePostListScreen(
            onDetailClick = onDetailClick,
            onCreateClick = onCreateClick,
            modifier = modifier,
        )
    }
}

fun NavController.toCodePostListScreen() {
    navigate(CodePostListScreenRoute) {
        launchSingleTop = true
    }
}