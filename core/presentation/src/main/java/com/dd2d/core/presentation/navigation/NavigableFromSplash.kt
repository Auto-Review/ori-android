package com.dd2d.core.presentation.navigation

import androidx.navigation.NavController

interface Navigable {
  /** 다음 화면으로 이동*/
  fun navigate(navController: NavController)
}