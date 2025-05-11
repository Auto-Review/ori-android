package com.dd2d.ori_android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.domain.auth_user.auth.model.AuthState
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import com.dd2d.ori_android.presentation_main._navigation.MainScreenRoute
import com.dd2d.presentation.auth._navigation.AuthScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {
    var startDestination by mutableStateOf<ScreenRoute?>(null)

    private suspend fun initStartDestination() {
        val authState = authRepository.getAuthState().getOrDefault(AuthState.SignOut)
        startDestination = when(authState) {
            AuthState.SignOut -> AuthScreenRoute
            AuthState.SignIn -> MainScreenRoute(selectedTabIndex = 2)
    
    private fun initAuthStateObserver() {
        authRepository.getAuthState()
            .onEach { state ->
                startDestination = when(state) {
                    AuthState.SignOut -> AuthScreenRoute
                    AuthState.SignIn -> MainScreenRoute(selectedTabIndex = 2)
                }
            }
            .launchIn(viewModelScope)
    }
    
        }
    }

    init {
        viewModelScope.launch {
            initStartDestination()
        }
        initAuthStateObserver()
    }
}