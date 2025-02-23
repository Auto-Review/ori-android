package com.dd2d.dat.user.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.dat.user.mapper.toUser
import com.dd2d.dat.user.mapper.toUserUpdateRequestDto
import com.dd2d.data_source.remote.server.user.UserApi
import com.dd2d.domain.user.model.User
import com.dd2d.domain.user.model.UserUpdater
import com.dd2d.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {
    override fun me(): Flow<DataState<User>> = flow {
        val response = userApi.me()
        emit(response.toUser())
    }.asDataState()

    override fun updateMe(update: UserUpdater): Flow<DataState<Boolean>> = flow {
        val response = userApi.updateMe(update.toUserUpdateRequestDto())
        emit(response)
    }.asDataState()
}