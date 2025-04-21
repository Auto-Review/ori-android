package com.dd2d.ori_android.presentation_main.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.navigation.BottomBarItem
import com.dd2d.core.presentation.navigation.BottomNavBar
import com.dd2d.core.presentation.navigation.DefaultNavHost
import com.dd2d.ori_android.presentation_main.compontnt.MainScreenFAB
import com.dd2d.ori_android.presentation_main.compontnt.MainScreenTopBar
import com.dd2d.ori_android.presentation_main.view_model.MainViewModel
import com.dd2d.presentation.code_post.create._navigation.toCodePostCreateScreen
import com.dd2d.presentation.code_post.detail._navigation.toCodePostScreen
import com.dd2d.presentation.code_post.list._navigation.CodePostListScreenRoute
import com.dd2d.presentation.code_post.list._navigation.routeCodePostListScreen
import com.dd2d.presentation.my._navigation.MyPageScreenRoute
import com.dd2d.presentation.my._navigation.routeMyPageScreen
import com.dd2d.presentation.my.model.MyPageNavigateEvent
import com.dd2d.presentation.schedule._navigation.ScheduleScreenRoute
import com.dd2d.presentation.schedule._navigation.routeScheduleScreen
import com.example.presentation.til.create._navigation.toTILCreateScreen
import com.example.presentation.til.detail._navigation.toTILScreen
import com.example.presentation.til.list._navigation.TILListScreenRoute
import com.example.presentation.til.list._navigation.routeTILListScreen

@Composable
private fun bottomBarColors(): NavigationBarItemColors {
    return remember {
        NavigationBarItemColors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,
            unselectedIconColor = Color.White.copy(alpha = 0.5F),
            unselectedTextColor = Color.White.copy(alpha = 0.5F),
            selectedIndicatorColor = Color.Transparent,
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified,
        )
    }
}

@Composable
fun MainScreen(
    appNavController: NavController,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val bottomBarItems = remember {
        listOf<BottomBarItem>(
            CodePostListScreenRoute,
            TILListScreenRoute,
            ScheduleScreenRoute,
            MyPageScreenRoute,
        )
    }

    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = { MainScreenTopBar(appNavController = appNavController) },
        floatingActionButton = {
            MainScreenFAB(
                options = listOf("CODE", "TIL"),
                onOptionClick = { index ->
                    when(index) {
                        0 -> appNavController.toCodePostCreateScreen()
                        1 -> appNavController.toTILCreateScreen()
                    }
                },
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = bottomBarItems,
                initialTabIndex = viewModel.route.selectedTabIndex,
                colors = bottomBarColors()
            )
        },
        modifier = modifier
    ){ inner ->
        DefaultNavHost(
            navController = navController,
            startDestination = bottomBarItems[viewModel.route.selectedTabIndex].route,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        ) {
            routeCodePostListScreen(onDetailClick = appNavController::toCodePostScreen,)
            routeTILListScreen(onTILClick = appNavController::toTILScreen)
            routeScheduleScreen()
            routeMyPageScreen(
                navigationEvent = { event ->
                    when(event) {
                        is MyPageNavigateEvent.CodePost -> appNavController.toCodePostScreen(codePostId = event.id)
                        is MyPageNavigateEvent.TIL -> appNavController.toTILScreen(tilId = event.id)
                    }
                }
            )
        }
    }
}