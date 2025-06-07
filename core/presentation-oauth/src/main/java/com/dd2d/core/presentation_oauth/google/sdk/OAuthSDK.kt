package com.dd2d.core.presentation_oauth.google.sdk

import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.core.presentation_oauth.google.model.OAuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class OAuthSDK {
  private val _state = MutableStateFlow<OAuthState>(OAuthState.Idle)
  val state = _state.asStateFlow()
  fun consumeState() = _state.update { OAuthState.Idle }
  protected fun stateToLoading() = _state.update { OAuthState.Loading }
  protected fun stateToError(exception: ManagedException) =
    _state.update { OAuthState.Error(exception = exception) }

  protected fun stateToSuccess(result: OAuthResult) =
    _state.update { OAuthState.Success(result = result) }

  protected fun stateTo(value: OAuthState) = _state.update { value }

  abstract fun signIn()
  abstract fun signOut()
  abstract fun withdraw()
}