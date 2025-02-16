package com.dd2d.domain.auth.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.auth.model.AuthRequester
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun auth(requester: AuthRequester): Flow<DataState<Boolean>>
}