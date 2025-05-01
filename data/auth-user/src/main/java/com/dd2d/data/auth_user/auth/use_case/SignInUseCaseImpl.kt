package com.dd2d.data.auth_user.auth.use_case

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.mapSuccess
import com.dd2d.core.data_store_manager.DataStoreManager
import com.dd2d.core.data_store_manager.Keys
import com.dd2d.core.token_manager.TokenManager
import com.dd2d.domain.auth_user.auth.model.AuthRequester
import com.dd2d.domain.auth_user.auth.model.AuthState
import com.dd2d.domain.auth_user.auth.repository.AuthRepository
import com.dd2d.domain.auth_user.auth.use_case.SignInUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager,
    private val tokenManager: TokenManager,
): SignInUseCase {
    override fun invoke(request: AuthRequester): Flow<DataState<Unit>> {
        return authRepository.auth(request)
            .mapSuccess {
                tokenManager.saveAuthToken(
                    accessToken = this.accessToken,
                    refreshToken = this.refreshToken,
                )
                dataStoreManager.saveValueByKey(key = Keys.getAuthStateKey(), value = AuthState.SignIn.ordinal)
            }
    }
}