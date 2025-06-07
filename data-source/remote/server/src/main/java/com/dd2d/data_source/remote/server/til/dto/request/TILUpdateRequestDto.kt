package com.dd2d.data_source.remote.server.til.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class TILUpdateRequestDto(
  val id: Int,
  val title: String? = null,
  val content: String? = null,
)
