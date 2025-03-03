package com.dd2d.domain.local_setting.repository

import com.dd2d.domain.local_setting.model.SignInState

interface LocalSettingRepository {
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    suspend fun getSignInState(): SignInState
    suspend fun setSignInState(state: SignInState)
}