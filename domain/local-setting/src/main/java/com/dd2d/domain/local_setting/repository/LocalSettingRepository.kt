package com.dd2d.domain.local_setting.repository

import com.dd2d.domain.local_setting.model.SignInState

interface LocalSettingRepository {

    @Deprecated("use SignInUseCase")
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    @Deprecated("use AuthRepository.getAuthState()")
    suspend fun getSignInState(): SignInState
    @Deprecated("use SignInUseCase")
    suspend fun setSignInState(state: SignInState)
}