package com.dd2d.domain.code_post.model.post

import com.dd2d.core.core.util.format
import java.time.LocalDateTime

data class CodePost(
    val id: Int,
    val author: CodePostAuthor,
    val title: String,
    val code: Code,
    val description: String,
    val reviewDate: LocalDateTime?,
    val level: Int,
    val createdAt: String,
) {
    companion object {
        val dummy = CodePost(
            id = 1,
            author = CodePostAuthor.dummy(),
            title = "코틀린",
            code = Code.dummy,
            description = "코틀린 좋아요 코틀린 지원 해줘",
            reviewDate = LocalDateTime.now(),
            level = 5,
            createdAt = LocalDateTime.now().format("yyyy.MM.dd"),
        )
    }
}
