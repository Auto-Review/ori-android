package com.dd2d.data_source.remote.server.user.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
  val id: Int,
  val nickname: String,
  val email: String,
)
