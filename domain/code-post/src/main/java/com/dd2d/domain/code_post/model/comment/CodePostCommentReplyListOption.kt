package com.dd2d.domain.code_post.model.comment

data class CodePostCommentReplyListOption(
    val codePostId: Int,
    val parentCommentId: Int,
    val page: Int = 0,
    val size: Int = 15
)
