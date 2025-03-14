package com.dd2d.domain.code_post.model.review

data class CodePostReviewUpdater(
    val id: Int,
    val email: String? = null,
    val review: String? = null,
    val code: String? = null,
)
