package com.dd2d.domain.user.model

@Deprecated("use auth_user.UserUpdater")
data class UserUpdater(
    val id: Int,
    val nickname: String? = null
)
