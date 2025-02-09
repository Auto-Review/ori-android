package com.dd2d.ori_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.presentation.code_post._navigation.CodePostList
import com.dd2d.presentation.code_post._navigation.codePostListScreen
import com.dd2d.presentation.code_post._navigation.codePostScreen

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CodePostList,
        modifier = modifier
    ) {
        codePostListScreen(navController)
        codePostScreen(navController)
    }

}