
package com.dd2d.domain.code_post.model.post

import com.dd2d.core.core.model.DateTimeString

data class CodePostListItem(
    val id: Int,
    val author: CodePostAuthor,
    val title: String,
    val description: String,
    val level: Int,
    val commentCount: Int,
    val createdAt: DateTimeString,
) {
    companion object {
        fun dummy(id: Int = 1) = CodePostListItem(
            id = id,
            author = CodePostAuthor.dummy(),
            title = "title1",
            description = "asdasdasd",
            level = 5,
            commentCount = 10,
            createdAt = "2025-02-08",
        )
    }
}