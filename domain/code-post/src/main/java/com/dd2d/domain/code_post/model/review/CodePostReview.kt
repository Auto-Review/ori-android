package com.dd2d.domain.code_post.model.review

import java.time.LocalDateTime

data class CodePostReview(
  val id: Int,
  val review: String,
  val code: String,
  val createdAt: LocalDateTime
) {
  companion object {
    fun dummy(id: Int = 1) = CodePostReview(
      id = id,
      review = "리뷰한 내용",
      code = "리뷰할 코드",
      createdAt = LocalDateTime.now(),
    )
  }
}
