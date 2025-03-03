package com.dd2d.domain.user.model

data class User(
    val id: Int,
    val nickname: String,
    val email: String,
) {
    companion object {
        fun dummy(id: Int = 1) = User(
            id = id,
            nickname = "JiY",
            email = "jiyong3954@gmail.com",
        )
    }
}
