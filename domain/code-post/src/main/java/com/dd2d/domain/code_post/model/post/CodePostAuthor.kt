package com.dd2d.domain.code_post.model.post

data class CodePostAuthor(
    val id: Int,
    val nickname: String,
    val email: String?,
) {
    companion object {
        fun dummy(id: Int = 1) = CodePostAuthor(
            id = id,
            nickname = "JiY",
            email = null,
        )
    }
}