package com.dd2d.data.code_post.mapper

import com.dd2d.core.core.util.format
import com.dd2d.core.core.util.utcToLocalDate
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostListItemResponseDto
import com.dd2d.domain.code_post.model.post.CodePostAuthor
import com.dd2d.domain.code_post.model.post.CodePostListItem

internal fun CodePostListItemResponseDto.toCodePostListItem(): CodePostListItem {
    return CodePostListItem(
        id = this.id,
        author = CodePostAuthor(
            id = this.member.id,
            nickname = this.member.nickname,
            email = this.member.email,
        ),
        title = this.title,
        description = this.description,
        level = this.level,
        createdAt = this.createdDate.utcToLocalDate().format("yyyy-MM-dd"),
    )
}
