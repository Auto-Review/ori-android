package com.dd2d.data.code_post.mapper.review

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewUpdateRequestDto
import com.dd2d.domain.code_post.model.review.CodePostReviewUpdater

internal fun CodePostReviewUpdater.toCodePostReviewUpdateRequestDto(): CodePostReviewUpdateRequestDto {
  return CodePostReviewUpdateRequestDto(
    id = this.id,
    email = this.email,
    description = this.review,
    code = this.code,
  )
}