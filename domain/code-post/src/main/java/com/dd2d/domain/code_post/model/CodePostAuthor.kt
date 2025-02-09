package com.dd2d.domain.code_post.model

data class CodePostAuthor(
    val id: Int,
    val nickname: String,
    val profileImageUrl: String?,
) {
    companion object {
        val dummy = CodePostAuthor(
            id = 1,
            nickname = "JiY",
            profileImageUrl = null,
        )
    }
}