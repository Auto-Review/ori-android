package com.dd2d.dat.user.mapper

import com.dd2d.data_source.remote.server.user.dto.response.UserResponseDto
import com.dd2d.domain.user.model.User

internal fun UserResponseDto.toUser(): User {
    return User(
        id = this.id,
        nickname = this.nickname,
        email = this.email,
    )
}