package com.dd2d.domain.code_post.model

import java.time.LocalDateTime

data class CodePostCreator(
    val title: String,
    val code: String,
    val description: String,
    val level: Int,
    val reviewDate: LocalDateTime?
)