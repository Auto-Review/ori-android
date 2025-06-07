package com.dd2d.data.code_post.mapper.comment

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCommentCreateRequestDto
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator

internal fun CodePostCommentCreator.toCodePostCommentCreateRequestDto(): CodePostCommentCreateRequestDto {
  return CodePostCommentCreateRequestDto(
    postId = this.postId,
    body = this.content,
    isPublic = this.isPublic,
    mentionNickName = this.mentionNickName,
    mentionEmail = this.mentionEmail,
    parentId = this.parentId,
  )
}