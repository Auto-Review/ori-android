package com.dd2d.domain.auth_user.auth.use_case

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.auth_user.auth.model.AuthRequester
import com.dd2d.domain.auth_user.auth.model.AuthState
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    /** 로그인 유즈케이스
     *
     * 내부적으로 `AuthToken` 저장, [AuthState.SignIn] 저장
     *
     * @param request 로그인 요청 시 필요한 데이터
     */
    operator fun invoke(request: AuthRequester): Flow<DataState<Unit>>
}