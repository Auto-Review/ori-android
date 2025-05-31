package com.dd2d.core.core.model

data class Pagination<T>(
    val list: List<T>,
    val currentPage: Int,
    val totalPage: Int,
    val totalItemCount: Int,
)

interface Pageable<T: Pageable<T>> {
    val page: Int
    val take: Int

    fun pageAt(page: Int): T
}