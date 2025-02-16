package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.toUTCString
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostCreateRequestDto
import com.dd2d.domain.code_post.model.CodePostCreator

internal fun CodePostCreator.toCorePostCreateRequestDto(): CodePostCreateRequestDto {
    return CodePostCreateRequestDto(
        title = this.title,
        level = this.level,
        reviewDay = this.reviewDate.toUTCString(),
        description = this.description,
        language = this.code.language,
        code = this.code.content,
    )
}