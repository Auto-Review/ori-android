package com.dd2d.domain.code_post.model.comment

data class CodePostCommentListOption(
  val codePostId: Int,
  val page: Int = 0,
  val size: Int = 10
)
