package com.dd2d.presentation.search.list._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.dd2d.presentation.search.list.screen.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchScreenRoute : ScreenRoute

fun NavGraphBuilder.routeSearchScreen(
  onBack: () -> Unit,
  onCodePostDetailClick: (codePostId: Int) -> Unit,
  onTILDetailClick: (tilId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  horizontalScreen<SearchScreenRoute> {
    SearchScreen(
      onBack = onBack,
      onCodePostDetailClick = onCodePostDetailClick,
      onTILDetailClick = onTILDetailClick,
      modifier = modifier,
    )
  }
}

fun NavController.toSearchScreen() {
  navigate(SearchScreenRoute) {
    launchSingleTop = true
  }
}