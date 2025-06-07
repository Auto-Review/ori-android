package com.dd2d.ori_android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.navigation.DefaultNavHost
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.safePopBackStack
import com.dd2d.ori_android.presentation_main._navigation.routeMainScreen
import com.dd2d.ori_android.presentation_main._navigation.toMainScreen
import com.dd2d.presentation.auth._navigation.routeAuthScreen
import com.dd2d.presentation.auth._navigation.toAuthScreen
import com.dd2d.presentation.code_post.create._navigation.routeCodePostCreateScreen
import com.dd2d.presentation.code_post.create._navigation.toCodePostCreateScreen
import com.dd2d.presentation.code_post.detail._navigation.routeCodePostScreen
import com.dd2d.presentation.code_post.detail._navigation.toCodePostScreen
import com.dd2d.presentation.code_post.review.routeCodePostReviewCreateScreen
import com.dd2d.presentation.code_post.review.toCodePostReviewCreateScreen
import com.dd2d.presentation.scrap.list._navigation.routeScrapScreen
import com.example.presentation.til.create._navigation.routeTILCreateScreen
import com.example.presentation.til.detail._navigation.routeTILScreen
import com.example.presentation.til.detail._navigation.toTILScreen

@Composable
internal fun AppNavHost(
  startDestination: ScreenRoute,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()

  AuthExpireHandler(onConfirm = navController::toAuthScreen)

  DefaultNavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
  ) {
    routeAuthScreen(onAuthSuccess = navController::toMainScreen)

    routeMainScreen(appNavController = navController)

    routeCodePostScreen(
      onBack = navController::safePopBackStack,
      onReviewCreateClick = { id ->
        navController.toCodePostReviewCreateScreen(codePostId = id, reviewId = null)
      },
      onReviewUpdateClick = { codePostId, reviewId ->
        navController.toCodePostReviewCreateScreen(codePostId = codePostId, reviewId = reviewId)
      },
      onCodePostUpdateClick = navController::toCodePostCreateScreen
    )
    routeCodePostCreateScreen(
      onBack = navController::safePopBackStack,
      moveToCodePost = navController::toCodePostScreen
    )
    routeCodePostReviewCreateScreen(onBack = navController::safePopBackStack)

    routeTILCreateScreen(
      onBack = navController::safePopBackStack,
      navigateToTILDetail = navController::toTILScreen
    )
    routeTILScreen(onBack = navController::safePopBackStack)

    routeScrapScreen(
      onClose = navController::safePopBackStack,
      navigateToCodePostDetail = navController::toCodePostScreen,
      navigateToTILDetail = navController::toTILScreen,
    )
  }
}
