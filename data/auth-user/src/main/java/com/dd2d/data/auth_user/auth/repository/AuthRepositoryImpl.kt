package com.dd2d.data.auth_user.auth.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.core.data_store_manager.DataStoreManager
import com.dd2d.core.data_store_manager.Keys
import com.dd2d.data.auth_user.auth.mapper.toAuthRequestDto
import com.dd2d.data_source.remote.server.auth.AuthApi
import com.dd2d.domain.auth_user.auth.model.AuthRequester
import com.dd2d.domain.auth_user.auth.model.AuthResult
import com.dd2d.domain.auth_user.auth.model.AuthState
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dataStoreManager: DataStoreManager,
): AuthRepository {
    override fun auth(requester: AuthRequester): Flow<DataState<AuthResult>> = flow {
        val response = authApi.auth(requester.toAuthRequestDto())

        emit(
            AuthResult(
                accessToken = response.accessToken?.substringAfter(" ")?: " ",
                refreshToken = response.refreshToken?: ""
            )
        )
    }.asDataState()


    override fun getAuthState(): Flow<AuthState> = flow {
        val ordinal = dataStoreManager.getValueByKey(key = Keys.getAuthStateKey(), default = -1)
        emit(AuthState.entries.getOrElse(ordinal) { AuthState.SignOut })
    }
}