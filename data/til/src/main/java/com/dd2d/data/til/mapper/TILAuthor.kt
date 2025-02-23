package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.response.TILAuthorResponseDto
import com.dd2d.domain.til.model.TILAuthor

internal fun TILAuthorResponseDto.toTILAuthor(): TILAuthor {
    return TILAuthor(
        id = this.id,
        nickname = this.nickname,
        email = this.email,
    )
}