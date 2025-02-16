package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.dateStringToLocalDateTime
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostResponseDto
import com.dd2d.domain.code_post.model.Code
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostAuthor

internal fun CodePostResponseDto.toCodePost(): CodePost {
    return CodePost(
        id = this.id,
        author = CodePostAuthor(
            id = this.memberDto.id,
            nickname = this.memberDto.nickname,
            email = this.memberDto.email,
        ),
        title = this.title,
        code = Code(
            language = this.language,
            content = this.code,
        ),
        description = this.description,
        reviewDate = this.reviewDay.dateStringToLocalDateTime(),
        level = this.level,
        createdAt = this.createDate,
    )
}