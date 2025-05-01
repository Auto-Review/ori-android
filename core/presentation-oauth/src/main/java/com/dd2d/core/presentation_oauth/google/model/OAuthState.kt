package com.dd2d.core.presentation_oauth.google.model

import com.dd2d.core.core.exception.ManagedException

sealed interface OAuthState {
    data object Idle: OAuthState
    data object Loading: OAuthState
    class Error(val exception: ManagedException): OAuthState
    data class Success(val result: OAuthResult): OAuthState
}