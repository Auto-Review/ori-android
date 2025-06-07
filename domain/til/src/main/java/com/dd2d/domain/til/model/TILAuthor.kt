package com.dd2d.domain.til.model

data class TILAuthor(
  val id: Int,
  val nickname: String,
  val email: String,
) {
  companion object {
    fun dummy(id: Int = 1) = TILAuthor(
      id = id,
      nickname = "닉네임",
      email = "jiyong3954@gmail.com",
    )
  }
}
