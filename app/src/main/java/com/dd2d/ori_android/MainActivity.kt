package com.dd2d.ori_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.core.presentation.theme.hansType
import com.dd2d.domain.auth_user.auth.model.AuthState
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import com.dd2d.ori_android.presentation_main._navigation.MainScreenRoute
import com.dd2d.presentation.auth._navigation.AuthScreenRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var authRepository: AuthRepository
    private var startDestination by mutableStateOf<ScreenRoute?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                LaunchedEffect(Unit) {
                    val authState = authRepository.getAuthState().getOrDefault(AuthState.SignOut)
                    startDestination = when(authState) {
                        AuthState.SignOut -> AuthScreenRoute
                        AuthState.SignIn -> MainScreenRoute(selectedTabIndex = 2)
                    }
                }
                CompositionLocalProvider(
                    LocalDensity provides Density(LocalDensity.current.density, 1F),
                    LocalHansType provides hansType
                ) {
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
}
