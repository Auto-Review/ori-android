package com.dd2d.ori_android

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.fcm.FCMModule
import com.dd2d.core.presentation.BuildConfig
import com.dd2d.core.presentation._ori.ReviewAlarmData
import com.dd2d.core.presentation._ori.ReviewTarget
import com.dd2d.core.presentation.navigation.ScreenRoute
import com.dd2d.domain.auth_user.auth.model.AuthState
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import com.dd2d.ori_android.presentation_main._navigation.MainScreenRoute
import com.dd2d.presentation.auth._navigation.AuthScreenRoute
import com.dd2d.presentation.code_post.detail._navigation.CodePostScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
  private val authRepository: AuthRepository,
) : ViewModel() {
  private val _appEventBus = MutableSharedFlow<AppEvent>(replay = 1)
  val appEventBus = _appEventBus.asSharedFlow()
  private fun newEvent(event: AppEvent) {
    viewModelScope.launch { _appEventBus.emit(event) }
  }

  var startDestination by mutableStateOf<ScreenRoute?>(null)

  private fun initAuthStateObserver() {
    authRepository.getAuthState()
      .onEach { state ->
        startDestination = when (state) {
          AuthState.SignOut -> AuthScreenRoute
          AuthState.SignIn -> MainScreenRoute(selectedTabIndex = 2)
        }
      }
      .launchIn(viewModelScope)
  }

  private fun checkFCMToken() {
    if (BuildConfig.DEBUG) {
      viewModelScope.launch {
        FCMModule.getFCMToken()
      }
    }
  }

  private fun reviewAlarmTask(intent: Intent) {
    val data = ReviewAlarmData.fromIntent(intent)?: return
    when(data.target) {
      is ReviewTarget.CodePost -> newEvent(NavigationEvent(CodePostScreenRoute(id = data.target.id)))
      is ReviewTarget.TIL -> {}
    }
  }

  fun handleIntent(intent: Intent) {
    viewModelScope.launch {
      launch { reviewAlarmTask(intent) }
    }
  }

  init {
    initAuthStateObserver()
    checkFCMToken()
  }
}