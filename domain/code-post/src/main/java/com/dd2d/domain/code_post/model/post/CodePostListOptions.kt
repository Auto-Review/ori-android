package com.dd2d.domain.code_post.model.post

import com.dd2d.core.core.model.Pageable

data class CodePostListOptions(
    override val page: Int = 0,
    override val take: Int = 15,
    val sort: Sort = Sort.CREATED_AT_DESC,
    val search: String = "",
    val language: Code.Language? = null,
): Pageable<CodePostListOptions> {
    override fun pageAt(page: Int): CodePostListOptions = copy(page = page)

    enum class Sort(val label: String, val sortByValue: String, val directionValue: String) {
        CREATED_AT_ASC(label = "생성일 오름차순", sortByValue = "createDate", directionValue = "asc"),
        CREATED_AT_DESC(label = "생성일 내림차순", sortByValue = "createDate", directionValue = "desc"),

        COMMENT_COUNT_ASC(label = "댓글 갯수 오름차순", sortByValue = "commentCount", directionValue = "asc"),
        COMMENT_COUNT_DESC(label = "댓글 갯수 내림차순", sortByValue = "commentCount", directionValue = "desc"),

        LEVEL_ASC(label = "난이도 오름차순", sortByValue = "level", directionValue = "asc"),
        LEVEL_DESC(label = "난이도 내림차순", sortByValue = "level", directionValue = "desc"),
    }
}