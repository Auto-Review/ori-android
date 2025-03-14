package com.dd2d.data.code_post.mapper.comment

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentDeleteRequestDto
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter

internal fun CodePostCommentDeleter.toCodePostCommentDeleteRequestDto(): CodePostCommentDeleteRequestDto {
    return CodePostCommentDeleteRequestDto(
        commentId = this.commentId,
        writerId = this.authorId,
    )
}