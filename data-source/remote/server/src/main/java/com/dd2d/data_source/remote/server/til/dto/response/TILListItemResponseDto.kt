package com.dd2d.data_source.remote.server.til.dto.response

import com.dd2d.core.core.model.UTCString
import kotlinx.serialization.Serializable

@Serializable
data class TILListItemResponseDto(
  val id: Int,
  val writerId: Int,
  val writerEmail: String,
  val writerNickName: String,
  val title: String,
  val content: String,
  val createdDate: UTCString,
)