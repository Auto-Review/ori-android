package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.dateStringToLocalDate
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostResponseDto
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.model.post.CodePostAuthor

internal fun CodePostResponseDto.toCodePost(): CodePost {
    return CodePost(
        id = this.id,
        author = CodePostAuthor(
            id = this.writerId,
            nickname = this.writerNickName,
            email = this.writerEmail,
        ),
        title = this.title,
        code = Code(
            language = this.language,
            content = this.code,
        ),
        description = this.description,
        reviewDate = this.reviewDay.dateStringToLocalDate("yyyy-MM-dd").atStartOfDay(),
        level = this.level,
        createdAt = this.createDate,
    )
}