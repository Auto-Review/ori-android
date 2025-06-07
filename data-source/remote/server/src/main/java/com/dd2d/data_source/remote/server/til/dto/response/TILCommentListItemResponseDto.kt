package com.dd2d.data_source.remote.server.til.dto.response


import kotlinx.serialization.Serializable

@Serializable
data class TILCommentListItemResponseDto(
  val id: Int,
  val parentId: Int,
  val writerId: Int,
  val writerNickName: String,
  val writerEmail: String,
  val mentionNickName: String,
  val mentionEmail: String,
  val body: String
)