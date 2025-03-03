package com.dd2d.data.auth.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.auth.mapper.toAuthRequestDto
import com.dd2d.data_source.remote.server.auth.AuthApi
import com.dd2d.domain.auth.model.AuthRequester
import com.dd2d.domain.auth.model.AuthResult
import com.dd2d.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
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
}