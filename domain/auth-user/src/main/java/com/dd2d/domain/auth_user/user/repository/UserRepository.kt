package com.dd2d.domain.auth_user.user.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.model.UserUpdater
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun me(): Flow<DataState<User>>
    fun updateMe(update: UserUpdater): Flow<DataState<Boolean>>

    suspend fun removeLocalData()
}