package com.dd2d.data_source.remote.server.user.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateRequestDto(
  val id: Int,
  val nickname: String? = null,
)
