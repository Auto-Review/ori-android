package com.dd2d.presentation.code_post.detail._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.Navigable
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.code_post.detail.screen.CodePostScreen
import kotlinx.serialization.Serializable

@Serializable
data class CodePostScreenRoute(val id: Int) : ScreenRoute, Navigable {
  override fun navigate(navController: NavController) {
    navController.toCodePostScreen(codePostId = id)
  }
}

fun NavGraphBuilder.routeCodePostScreen(
  onBack: () -> Unit,
  onReviewCreateClick: (codePostId: Int) -> Unit,
  onReviewUpdateClick: (codePostId: Int, reviewId: Int) -> Unit,
  onCodePostUpdateClick: (codePostId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  horizontalScreen<CodePostScreenRoute> {
    CodePostScreen(
      onBack = onBack,
      onReviewCreateClick = onReviewCreateClick,
      onReviewUpdateClick = onReviewUpdateClick,
      onCodePostUpdateClick = onCodePostUpdateClick,
      modifier = modifier,
    )
  }
}

fun NavController.toCodePostScreen(codePostId: Int) {
  navigate(CodePostScreenRoute(id = codePostId)) {
    launchSingleTop = true
  }
}