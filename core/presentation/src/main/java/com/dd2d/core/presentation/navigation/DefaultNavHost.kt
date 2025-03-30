package com.dd2d.core.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun DefaultNavHost(
	navController: NavHostController,
	startDestination: ScreenRoute,
	modifier: Modifier = Modifier,
	contentAlignment: Alignment = Alignment.TopCenter,
	builder: NavGraphBuilder.() -> Unit
) {
	NavHost(
		navController = navController,
		startDestination = startDestination,
		contentAlignment = contentAlignment,
		enterTransition = { EnterTransition.None },
		exitTransition = { ExitTransition.None },
		popEnterTransition = { EnterTransition.None },
		popExitTransition = { ExitTransition.None },
		sizeTransform = null,
		modifier = modifier,
		builder = builder,
	)
}