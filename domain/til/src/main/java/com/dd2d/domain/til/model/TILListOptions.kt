package com.dd2d.domain.til.model

data class TILListOptions(
    val page: Int = 0,
    val take: Int = 20,
    val search: String = "",
)
