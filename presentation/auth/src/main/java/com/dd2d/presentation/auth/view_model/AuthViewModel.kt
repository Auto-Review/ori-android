package com.dd2d.presentation.auth.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.core.presentation_oauth.google.model.OAuthResult
import com.dd2d.domain.auth.model.AuthRequester
import com.dd2d.domain.auth.repository.AuthRepository
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val localSettingRepository: LocalSettingRepository,
): ViewModel(), UIStateManager {
    override val uiState = MutableStateFlow<UIState>(UIState.Idle)

    fun auth(oAuthResult: OAuthResult) {
        authRepository.auth(requester = AuthRequester(oAuthToken = oAuthResult.token))
            .onEach { state ->
                when(state) {
                    is DataState.Loading -> stateToLoading()
                    is DataState.Error -> stateToError(state.exception)
                    is DataState.Success -> {
                        localSettingRepository.saveAccessToken(accessToken = state.data.accessToken)
                        stateToSuccess()
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}
