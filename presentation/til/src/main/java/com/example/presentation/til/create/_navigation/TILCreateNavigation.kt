package com.example.presentation.til.create._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.example.presentation.til.create.screen.TILCreateScreen
import kotlinx.serialization.Serializable

@Serializable
data object TILCreateScreenRoute : ScreenRoute

fun NavGraphBuilder.routeTILCreateScreen(
    onBack: () -> Unit,
    navigateToTILDetail: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    horizontalScreen<TILCreateScreenRoute> {
        TILCreateScreen(
            onBack = onBack,
            navigateToTILDetail = navigateToTILDetail,
            modifier = modifier,
        )
    }
}

fun NavController.toTILCreateScreen() {
    navigate(TILCreateScreenRoute) {
        launchSingleTop = true
    }
}