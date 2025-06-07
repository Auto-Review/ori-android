package com.dd2d.data.code_post.mapper.review

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewDeleteRequestDto
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter

internal fun CodePostReviewDeleter.toCodePostReviewDeleteRequestDto(): CodePostReviewDeleteRequestDto {
  return CodePostReviewDeleteRequestDto(
    id = this.id,
    email = this.email,
  )
}