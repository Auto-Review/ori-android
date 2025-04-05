package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.response.TILResponseDto
import com.dd2d.domain.til.model.TIL
import com.dd2d.domain.til.model.TILAuthor

internal fun TILResponseDto.toTIL(): TIL {
    return TIL(
        id = this.id,
        title = this.title,
        content = this.content,
        author = TILAuthor(
            id = this.writerId,
            nickname = this.writerNickName,
            email = this.writerEmail,
        ),
        createdAt = this.createDate,
    )
}