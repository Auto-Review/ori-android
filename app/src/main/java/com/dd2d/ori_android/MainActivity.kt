package com.dd2d.ori_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.local_setting.model.SignInState
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import com.dd2d.ori_android.navigation.AppNavHost
import com.dd2d.ori_android.navigation.main.MainScreen
import com.dd2d.presentation.auth._navigation.AuthScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var localSettingRepository: LocalSettingRepository
    private var startDestination by mutableStateOf<Any?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                LaunchedEffect(Unit) {
                    startDestination = when(localSettingRepository.getSignInState()) {
                        SignInState.SignOut -> AuthScreen
                        SignInState.SignIn -> MainScreen
                    }
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
