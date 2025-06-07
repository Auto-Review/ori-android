package com.dd2d.data.auth_user.user.mapper

import com.dd2d.data_source.remote.server.user.dto.request.UserUpdateRequestDto
import com.dd2d.domain.auth_user.user.model.UserUpdater

internal fun UserUpdater.toUserUpdateRequestDto(): UserUpdateRequestDto {
  return UserUpdateRequestDto(
    id = this.id,
    nickname = this.nickname,
  )
}