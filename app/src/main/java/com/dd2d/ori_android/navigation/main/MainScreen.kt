package com.dd2d.ori_android.navigation.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.core.presentation.navigation.navigateWithClearBackStack
import com.dd2d.ori_android.R
import com.dd2d.presentation.code_post._navigation.CodePostListNavigator
import com.dd2d.presentation.code_post._navigation.CodePostListScreen
import com.dd2d.presentation.code_post._navigation.codePostListScreen
import com.dd2d.presentation.code_post._navigation.toCodePost
import com.dd2d.presentation.code_post._navigation.toCodePostCreate
import com.dd2d.presentation.my._navigation.MyPageNavigator
import com.dd2d.presentation.my._navigation.MyPageScreen
import com.dd2d.presentation.my._navigation.myPageScreen
import com.dd2d.presentation.schedule._navigation.ScheduleScreen
import com.dd2d.presentation.schedule._navigation.scheduleScreen
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@OptIn(ExperimentalMaterial3Api::class)
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
                    labelRes = com.dd2d.core.presentation.R.string.tab_code,
                    destination = CodePostListScreen
                ),
                BottomBarItem(
                    iconRes = R.drawable.tab_til,
                    labelRes = com.dd2d.core.presentation.R.string.tab_til,
                    destination = ScheduleScreen
                ),
                BottomBarItem(
                    iconRes = R.drawable.tab_main,
                    labelRes = com.dd2d.core.presentation.R.string.tab_main,
                    destination = ScheduleScreen
                ),
                BottomBarItem(
                    iconRes = R.drawable.tab_my,
                    labelRes = com.dd2d.core.presentation.R.string.tab_my,
                    destination = MyPageScreen
                ),
                BottomBarItem(
                    iconRes = R.drawable.tab_setting,
                    labelRes = com.dd2d.core.presentation.R.string.tab_setting,
                    destination = ScheduleScreen
                )
            )
        }

        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = { PainterImage(res = com.dd2d.core.presentation.R.drawable.logo) },
                    actions = {
                        VectorIconButton(res = com.dd2d.core.presentation.R.drawable.search) {

                        }
                        VectorIconButton(res = com.dd2d.core.presentation.R.drawable.scrap) {

                        }
                        VectorIconButton(res = com.dd2d.core.presentation.R.drawable.off_notification) {

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    )
                )
            },
            bottomBar = {
                MainBottomBar(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    items = items,
                    onClick = { destination->
                        navController.navigateWithClearBackStack(route = destination, launchSingleTop = true)
                    },
                    initialIndex = 2,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            modifier = modifier
        ){ inner ->
            NavHost(
                navController = navController,
                startDestination = ScheduleScreen,
                modifier = Modifier
                    .consumeWindowInsets(inner)
                    .fillMaxSize()
                    .padding(inner)
            ) {
                codePostListScreen(
                    navigationEvent = { destination ->
                        when(destination) {
                            is CodePostListNavigator.CodePost -> appNavController.toCodePost(id =  destination.id)
                            is CodePostListNavigator.CodePostCreate -> appNavController.toCodePostCreate()
                        }
                    }
                )
                scheduleScreen(
                    navController = navController,
                    onCodePostCreateClick = appNavController::toCodePostCreate,
                    onTILPostCreateClick = {

                    }
                )
                myPageScreen { destination ->
                    when(destination) {
                        is MyPageNavigator.CodePost -> appNavController.toCodePost(destination.id)
                        is MyPageNavigator.CodePostCreate -> appNavController.toCodePostCreate()
                        is MyPageNavigator.TIL -> {}
                        is MyPageNavigator.TILPostCreate -> {}
                    }
                }
            }
        }
    }
}

fun NavController.toMain() {
    navigateWithClearBackStack(route = MainScreen, launchSingleTop = true)
}