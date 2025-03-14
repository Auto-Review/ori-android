package com.dd2d.domain.code_post.model.comment

data class CodePostCommentCreator(
    val postId: Int,
    val parentId: Int?,
    val content: String,
    val isPublic: Boolean,
    val mentionNickName: String?,
    val mentionEmail: String?
)
