package com.dd2d.data.code_post.mapper.review

import com.dd2d.core.core.util.utcToLocalDateTime
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostReviewResponseDto
import com.dd2d.domain.code_post.model.review.CodePostReview

internal fun CodePostReviewResponseDto.toCodePostReview(): CodePostReview {
  return CodePostReview(
    id = this.id,
    review = this.description,
    code = this.code,
    createdAt = this.createdAt.utcToLocalDateTime(),
  )
}