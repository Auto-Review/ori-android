package com.dd2d.presentation.scrap.list._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.scrap.list.screen.ScrapScreen
import kotlinx.serialization.Serializable

@Serializable
data object ScrapScreenRoute : ScreenRoute

fun NavGraphBuilder.routeScrapScreen(
    onClose: () -> Unit,
    navigateToCodePostDetail: (codePostId: Int) -> Unit,
    navigateToTILDetail: (tilId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    horizontalScreen<ScrapScreenRoute> {
        ScrapScreen(
            onClose = onClose,
            navigateToCodePostDetail = navigateToCodePostDetail,
            navigateToTILDetail = navigateToTILDetail,
            modifier = modifier,
        )
    }
}

fun NavController.toScrapScreen() {
    navigate(ScrapScreenRoute) {
        launchSingleTop = true
    }
}