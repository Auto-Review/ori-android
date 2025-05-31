package com.dd2d.presentation.code_post.create._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.code_post.create.screen.CodePostCreateScreen
import kotlinx.serialization.Serializable

@Serializable
data object CodePostCreateScreenRoute : ScreenRoute

fun NavGraphBuilder.routeCodePostCreateScreen(
    onBack: () -> Unit,
    moveToCodePost: (codePostId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    horizontalScreen<CodePostCreateScreenRoute> {
        CodePostCreateScreen(
            onBack = onBack,
            moveToCodePost = moveToCodePost,
            modifier = modifier,
        )
    }
}

fun NavController.toCodePostCreateScreen() {
    navigate(CodePostCreateScreenRoute) {
        launchSingleTop = true
    }
}