package com.dd2d.data_source.remote.server.til.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class TILCreateRequestDto(
  val title: String,
  val content: String,
)
