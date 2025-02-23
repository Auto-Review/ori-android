package com.dd2d.domain.til.model

data class TIL(
    val id: Int,
    val title: String,
    val content: String,
    val author: TILAuthor,
    val createdAt: String,
)
