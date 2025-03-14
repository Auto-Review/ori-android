package com.dd2d.data.code_post.mapper.comment

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentUpdateRequestDto
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater

internal fun CodePostCommentUpdater.toCodePostCommentUpdateRequestDto(): CodePostCommentUpdateRequestDto {
    return CodePostCommentUpdateRequestDto(
        commentId = this.commentId,
        writerNickName = null,
        writerEmail = null,
        body = this.content,
        isPublic = this.isPublic,
        mentionNickName = this.mentionNickName,
        mentionEmail = this.mentionEmail,
    )
}