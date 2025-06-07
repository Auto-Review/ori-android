package com.example.presentation.til.detail._navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.navigation.horizontalScreen
import com.example.presentation.til.detail.screen.TILScreen
import kotlinx.serialization.Serializable

@Serializable
data class TILScreenRoute(val id: Int) : ScreenRoute

fun NavGraphBuilder.routeTILScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  horizontalScreen<TILScreenRoute> {
    TILScreen(
      onBack = onBack,
      modifier = modifier,
    )
  }
}

fun NavController.toTILScreen(tilId: Int) {
  navigate(TILScreenRoute(id = tilId)) {
    launchSingleTop = true
  }
}