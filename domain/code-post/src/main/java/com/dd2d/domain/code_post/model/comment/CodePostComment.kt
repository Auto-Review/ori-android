package com.dd2d.domain.code_post.model.comment

data class CodePostComment(
    val id: Int,
    val parentCommentId: Int?,
    val author: CodePostCommentAuthor,
    val mentionNickname: String,
    val mentionEmail: String,
    val content: String,
)
