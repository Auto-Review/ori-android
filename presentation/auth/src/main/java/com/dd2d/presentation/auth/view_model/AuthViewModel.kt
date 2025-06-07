package com.dd2d.presentation.auth.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.fcm.FCMModule
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.domain.auth_user.auth.model.AuthRequester
import com.dd2d.domain.auth_user.auth.use_case.SignInUseCase
import com.dd2d.domain.auth_user.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
  private val signInUseCase: SignInUseCase,
  private val userRepository: UserRepository,
) : ViewModel(), UIStateManager {
  override val uiState = MutableStateFlow<UIState>(UIState.Idle)

  fun auth(oAuthResult: OAuthResult) {
    signInUseCase(request = AuthRequester(oAuthToken = oAuthResult.token))
      .onEachState(
        onLoading = { uiState.stateToLoading() },
        onError = { uiState.stateToError(it) },
        onSuccess = {
          userRepository.updateFCMToken(FCMModule.getFCMToken())
          uiState.stateToSuccess()
        },
      )
      .launchIn(viewModelScope)
  }
}
