package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.request.TILUpdateRequestDto
import com.dd2d.domain.til.model.TILUpdater

internal fun TILUpdater.toTILUpdateRequestDto(): TILUpdateRequestDto {
    return TILUpdateRequestDto(
        id = this.id,
        title = this.title,
        content = this.content,
    )
}