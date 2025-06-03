package com.dd2d.domain.code_post.model.post

import java.time.LocalDateTime

data class CodePostUpdater(
    val id: Int,
    val title: String? = null,
    val code: Code? = null,
    val description: String? = null,
    val level: Int? = null,
    val isPublic: Boolean? = null,
    val reviewDate: LocalDateTime? = null
)