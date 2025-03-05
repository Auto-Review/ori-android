package com.dd2d.domain.code_post.model

data class CodePostListOptions(
    val page: Int = 0,
    val take: Int = 15,
    val sort: String? = null,
    val search: String = "",
)