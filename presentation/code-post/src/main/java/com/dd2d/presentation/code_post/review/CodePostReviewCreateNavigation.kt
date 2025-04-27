package com.dd2d.presentation.code_post.review


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.code_post.review.screen.CodePostReviewCreateScreen
import kotlinx.serialization.Serializable

@Serializable
data class CodePostReviewCreateScreenRoute(val codePostId: Int, val reviewId: Int?) : ScreenRoute

fun NavGraphBuilder.routeCodePostReviewCreateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    horizontalScreen<CodePostReviewCreateScreenRoute> {
        CodePostReviewCreateScreen(
            onBack = onBack,
            modifier = modifier,
        )
    }
}

fun NavController.toCodePostReviewCreateScreen(codePostId: Int, reviewId: Int?) {
    navigate(CodePostReviewCreateScreenRoute(codePostId = codePostId, reviewId = reviewId)) {
        launchSingleTop = true
    }
}