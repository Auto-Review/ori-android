package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.format
import com.dd2d.core.core.util.utcToLocalDate
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostScrapListResponseDto
import com.dd2d.domain.code_post.model.scrap.CodePostScrapListItem

internal fun CodePostScrapListResponseDto.toCodePostScrapListItem(): CodePostScrapListItem {
    return CodePostScrapListItem(
        id = this.id,
        codePostId = this.codePostId,
        codePostTitle = this.codePostTitle,
        commentCount = this.commentCount,
        writer = this.writer,
        createdAt = this.updateAt.utcToLocalDate().format("yyyy-MM-dd")
    )
}