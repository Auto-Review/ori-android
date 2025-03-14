package com.dd2d.data.code_post.mapper.review

import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostReviewCreateRequestDto
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator

internal fun CodePostReviewCreator.toCodePostReviewCreateRequestDto(): CodePostReviewCreateRequestDto {
    return CodePostReviewCreateRequestDto(
        codePostId = this.codePostId,
        code = this.code,
        description = this.review,
    )
}