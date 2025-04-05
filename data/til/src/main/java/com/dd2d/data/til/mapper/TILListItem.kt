package com.dd2d.data.til.mapper

import com.dd2d.core.core.util.format
import com.dd2d.core.core.util.utcToLocalDateTime
import com.dd2d.data_source.remote.server.til.dto.response.TILListItemResponseDto
import com.dd2d.domain.til.model.TILAuthor
import com.dd2d.domain.til.model.TILListItem

internal fun TILListItemResponseDto.toTILListItem(): TILListItem {
    return TILListItem(
        id = this.id,
        title = this.title,
        content = this.content,
        author = TILAuthor(
            id = this.writerId,
            nickname = this.writerNickName,
            email = this.writerEmail,
        ),
        createdAt = this.createdDate.utcToLocalDateTime().format("yyyy-MM-dd"),
    )
}