package com.dd2d.ori_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.navigation.compose.rememberNavController
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.core.presentation.theme.hansType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val appViewModel by viewModels<AppViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()

    super.onCreate(savedInstanceState)

    setContent {
      AppTheme {
        CompositionLocalProvider(
          LocalDensity provides Density(LocalDensity.current.density, 1F),
          LocalHansType provides hansType
        ) {
          val navController = rememberNavController()
          appViewModel.startDestination?.let { destination ->
            AppEventHandler(
              event = appViewModel.appEventBus,
              navController = navController
            )
            AppNavHost(
              startDestination = destination,
              navController = navController,
              startAppEventHandling = { appViewModel.handleIntent(intent) },
              modifier = Modifier.fillMaxSize()
            )
          }
        }
      }
    }
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    appViewModel.handleIntent(intent)
  }
}
