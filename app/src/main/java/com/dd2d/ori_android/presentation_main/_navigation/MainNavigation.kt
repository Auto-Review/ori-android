package com.dd2d.ori_android.presentation_main._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.fadeScreen
import com.dd2d.ori_android.presentation_main.screen.MainScreen
import kotlinx.serialization.Serializable

@Serializable
data class MainScreenRoute(val selectedTabIndex: Int) : ScreenRoute

fun NavGraphBuilder.routeMainScreen(
  appNavController: NavController,
  modifier: Modifier = Modifier,
) {
  fadeScreen<MainScreenRoute> {
    MainScreen(
      appNavController = appNavController,
      modifier = modifier,
    )
  }
}

fun NavController.toMainScreen(selectedTabIndex: Int = 2) {
  navigate(MainScreenRoute(selectedTabIndex = selectedTabIndex)) {
    launchSingleTop = true
  }
}