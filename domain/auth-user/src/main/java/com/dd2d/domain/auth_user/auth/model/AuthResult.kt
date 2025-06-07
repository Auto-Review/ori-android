package com.dd2d.domain.auth_user.auth.model

data class AuthResult(
  val accessToken: String,
  val refreshToken: String,
)
