package com.dd2d.data.auth_user.user.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.auth_user.user.mapper.toUser
import com.dd2d.data.auth_user.user.mapper.toUserUpdateRequestDto
import com.dd2d.data_source.remote.server.user.UserApi
import com.dd2d.data_source.remote.server.user.dto.request.FCMUpdateRequestDto
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.auth_user.user.model.UserUpdater
import com.dd2d.domain.auth_user.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
  private val userApi: UserApi
) : UserRepository {
  private var cached: User? = null
  override fun me(): Flow<DataState<User>> = flow {
    val me = cached ?: userApi.me().toUser().also { cached = it }

    emit(me)
  }.asDataState()

  override fun updateMe(update: UserUpdater): Flow<DataState<Boolean>> = flow {
    val response = userApi.updateMe(update.toUserUpdateRequestDto())
    cached = null
    emit(response)
  }.asDataState()

  override suspend fun updateFCMToken(fcmToken: String): Result<Unit> {
    return userApi.runCatching { setFCMToken(FCMUpdateRequestDto(fcmToken)) }.map { }
  }

  override suspend fun removeLocalData() {
    cached = null
  }
}