package com.dd2d.data_source.remote.server.code_post.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CodePostCommentCreateRequestDto(
  val postId: Int,
  val body: String,
  val isPublic: Boolean,
  val mentionNickName: String?,
  val mentionEmail: String?,
  val parentId: Int?
)