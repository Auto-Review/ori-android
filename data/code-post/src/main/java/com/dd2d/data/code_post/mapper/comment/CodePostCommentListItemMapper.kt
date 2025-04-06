package com.dd2d.data.code_post.mapper.comment

import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostCommentListItemResponseDto
import com.dd2d.domain.code_post.model.comment.CodePostCommentAuthor
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem

internal fun CodePostCommentListItemResponseDto.toCodePostCommentListItem(): CodePostCommentListItem {
    return CodePostCommentListItem(
        id = this.id,
        parentCommentId = this.id,
        author = CodePostCommentAuthor(id = this.writerId, nickname = this.writerNickName, email = this.writerEmail),
        mentionNickname = this.mentionEmail,
        mentionEmail = this.writerEmail,
        content = this.body,
        createdAt = "TODO: 날짜 데이터 필요"
    )
}