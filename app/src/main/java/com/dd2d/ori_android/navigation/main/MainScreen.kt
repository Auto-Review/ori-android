package com.dd2d.ori_android.navigation.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.navigation.navigateWithClearBackStack
import com.dd2d.ori_android.R
import com.dd2d.presentation.code_post._navigation.toCodePost
import com.dd2d.presentation.code_post._navigation.toCodePostCreate
import com.dd2d.presentation.code_post.screen.CodePostListScreen
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

sealed interface MainScreenDestination {
    @Serializable data object CodePostList: MainScreenDestination
}

fun NavGraphBuilder.mainScreen(
    appNavController: NavController,
    modifier: Modifier = Modifier
) {

    composable<MainScreen> {
        val navController = rememberNavController()

        val items = remember {
            listOf(
                BottomBarItem(
                    iconRes = R.drawable.tab_code,
                    labelRes = null,
                    destination = MainScreenDestination.CodePostList
                )
            )
        }

        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                MainBottomBar(
                    items = items,
                    onClick = { destination->
                        navController.navigateWithClearBackStack(route = destination, launchSingleTop = true)
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            modifier = modifier
        ){ inner ->
            NavHost(
                navController = navController,
                startDestination = MainScreenDestination.CodePostList,
                modifier = Modifier
                    .consumeWindowInsets(inner)
                    .fillMaxSize()
                    .padding(inner)
            ) {
                composable<MainScreenDestination.CodePostList> {
                    CodePostListScreen(
                        onCodePostClick = appNavController::toCodePost,
                        onCreateClick = appNavController::toCodePostCreate,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

fun NavController.toMain() {
    navigateWithClearBackStack(route = MainScreen, launchSingleTop = true)
}