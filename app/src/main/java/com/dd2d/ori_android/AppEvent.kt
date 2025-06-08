package com.dd2d.ori_android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.dd2d.core.presentation.navigation.Navigable
import kotlinx.coroutines.flow.SharedFlow

interface AppEvent

class NavigationEvent(val route: Navigable) : AppEvent

@Composable
fun AppEventHandler(
  event: SharedFlow<AppEvent>,
  navController: NavController,
) {

  LaunchedEffect(key1 = Unit) {
    event
      .collect { event ->
        when(event) {
          is NavigationEvent -> {
            event.route.navigate(navController = navController)
          }
        }
      }
  }
}