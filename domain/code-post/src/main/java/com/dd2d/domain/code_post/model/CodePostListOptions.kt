package com.dd2d.domain.code_post.model

data class CodePostListOptions(
    val page: Int = 1,
    val take: Int = 15,
    val search: String = "",
)