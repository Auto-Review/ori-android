package com.dd2d.domain.auth.model

data class AuthResult(
    val accessToken: String,
    val refreshToken: String,
)
