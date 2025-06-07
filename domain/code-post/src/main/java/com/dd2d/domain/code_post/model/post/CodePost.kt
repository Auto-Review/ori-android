package com.dd2d.domain.code_post.model.post

import com.dd2d.core.core.model.DateTimeString
import com.dd2d.core.core.util.format
import java.time.LocalDateTime

data class CodePost(
  val id: Int,
  val author: CodePostAuthor,
  val title: String,
  val code: Code,
  val description: String,
  val reviewDate: LocalDateTime?,
  val level: Int,
  val isPublic: Boolean,
  val isScrapped: Boolean,
  val createdAt: DateTimeString,
) {
  companion object {
    fun dummy(id: Int = 1) = CodePost(
      id = id,
      author = CodePostAuthor.dummy(),
      title = "코틀린",
      code = Code.dummy,
      description = "코틀린 좋아요 코틀린 지원 해줘",
      reviewDate = LocalDateTime.now(),
      level = 5,
      isPublic = true,
      isScrapped = false,
      createdAt = LocalDateTime.now().format("yyyy.MM.dd"),
    )
  }
}
