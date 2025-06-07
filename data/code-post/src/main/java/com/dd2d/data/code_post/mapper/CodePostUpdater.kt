package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.toUTCString
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostUpdateRequestDto
import com.dd2d.domain.code_post.model.post.CodePostUpdater

internal fun CodePostUpdater.toCodePostUpdateRequestDto(): CodePostUpdateRequestDto {
  return CodePostUpdateRequestDto(
    id = this.id,
    title = this.title,
    description = this.description,
    level = this.level,
    reviewDay = this.reviewDate?.toUTCString(),
    language = this.code?.language?.value,
    code = this.code?.content,
    isPublic = this.isPublic
  )
}