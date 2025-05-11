package com.dd2d.domain.auth_user.auth.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.auth_user.auth.model.AuthRequester
import com.dd2d.domain.auth_user.auth.model.AuthResult
import com.dd2d.domain.auth_user.auth.model.AuthState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun auth(requester: AuthRequester): Flow<DataState<AuthResult>>
    fun getAuthState(): Flow<AuthState>
}