package com.dd2d.domain.code_post.model

import java.time.LocalDateTime

data class CodePostCreator(
    val title: String,
    val level: Int,
    val code: Code,
    val description: String,
    val reviewDate: LocalDateTime?
)