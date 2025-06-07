package com.dd2d.core.presentation_oauth.google.model

data class OAuthResult(
  val token: String,
  val name: String? = null,
  val email: String? = null,
  val profileImageUrl: String? = null,
) {
  override fun toString(): String = """
        OAuthResult(
            token = "$token",
            name = "$name",
            email = "$email",
            profileImageUrl = "$profileImageUrl",
        )
    """.trimIndent()
}
