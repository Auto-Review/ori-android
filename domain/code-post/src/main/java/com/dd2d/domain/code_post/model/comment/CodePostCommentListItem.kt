package com.dd2d.domain.code_post.model.comment

import com.dd2d.core.core.model.DateTimeString

data class CodePostCommentListItem(
    val id: Int,
    val parentCommentId: Int?,
    val author: CodePostCommentAuthor,
    val mentionNickname: String?,
    val mentionEmail: String?,
    val content: String,
    val createdAt: DateTimeString,
) {
    companion object {
        fun dummy(id: Int = 1) = CodePostCommentListItem(
            id = id,
            parentCommentId = 1,
            author = CodePostCommentAuthor(
                id = 1,
                nickname = "닉네임 $id",
                email = "email $id",
            ),
            mentionNickname = null,
            mentionEmail = null,
            content = "댓글 내용 $id",
            createdAt = "2025-04-06 21:02",
        )
    }
}