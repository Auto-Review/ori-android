package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.response.TILResponseDto
import com.dd2d.domain.til.model.TIL

internal fun TILResponseDto.toTIL(): TIL {
    return TIL(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.member.toTILAuthor(),
        createdAt = this.createDate,
    )
}