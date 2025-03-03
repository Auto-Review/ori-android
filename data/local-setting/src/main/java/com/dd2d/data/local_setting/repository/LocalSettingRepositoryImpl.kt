package com.dd2d.data.local_setting.repository

import com.dd2d.core.data_store_manager.DataStoreManager
import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data.local_setting.model.SignInStateKey
import com.dd2d.domain.local_setting.model.SignInState
import com.dd2d.domain.local_setting.repository.LocalSettingRepository
import javax.inject.Inject

class LocalSettingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val tokenManager: TokenManager
): LocalSettingRepository {
    override suspend fun saveAuthToken(accessToken: String, refreshToken: String) {
        tokenManager.saveAuthToken(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override suspend fun getSignInState(): SignInState {
        return dataStoreManager.getValueByKey(key = SignInStateKey, default = SignInState.SignOut.ordinal)
            .let { value ->
                SignInState.entries[value]
            }
    }

    override suspend fun setSignInState(state: SignInState) {
        dataStoreManager.saveValueByKey(key = SignInStateKey, value = state.ordinal)
    }
}