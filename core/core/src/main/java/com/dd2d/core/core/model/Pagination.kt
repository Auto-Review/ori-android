package com.dd2d.core.core.model

data class Pagination<T>(
    val list: List<T>,
    val currentPage: Int,
    val totalPage: Int,
    val totalItemCount: Int,
)
