package com.dd2d.domain.code_post.model.comment

data class CodePostCommentUpdater(
  val commentId: Int,
  val content: String? = null,
  val isPublic: Boolean? = null,
  val mentionNickName: String? = null,
  val mentionEmail: String? = null,
)
