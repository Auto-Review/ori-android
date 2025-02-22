package com.dd2d.ori_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import com.dd2d.ori_android.navigation.AppNavHost
import com.dd2d.ori_android.navigation.main.MainScreen
import com.dd2d.presentation.auth._navigation.AuthScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var localSettingRepository: LocalSettingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            var startDestination by remember { mutableStateOf<Any?>(null) }
            AppTheme {
                LaunchedEffect(Unit) {
                    val token = localSettingRepository.getAccessToken()
                    startDestination =
                        if(token.isBlank()) AuthScreen
                        else MainScreen
                }
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxSize()
                ) {
                    startDestination?.let { destination ->
                        AppNavHost(
                            startDestination = destination,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
