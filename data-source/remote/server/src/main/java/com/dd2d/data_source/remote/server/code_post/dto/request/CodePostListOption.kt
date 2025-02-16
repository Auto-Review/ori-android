package com.dd2d.data_source.remote.server.code_post.dto.request

data class CodePostListOption(
    val page: Int = 1,
    val take: Int = 10,
    val sort: String? = null,
)
