package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import com.dd2d.domain.til.model.TILListItem

internal fun TILListItemResponseDto.toTILListItem(): TILListItem {
    return TILListItem(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.member.toTILAuthor(),
        createdAt = this.createDate,
    )
}