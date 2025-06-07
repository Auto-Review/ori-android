package com.dd2d.domain.til.model

import com.dd2d.core.core.model.DateTimeString
import java.time.LocalDateTime

data class TIL(
  val id: Int,
  val title: String,
  val content: String,
  val author: TILAuthor,
  val createdAt: DateTimeString,
) {
  companion object {
    fun dummy(id: Int = 1) = TIL(
      id = id,
      title = "${id}번째 TIL 제목",
      content = "${id}번째 TIL 내용",
      author = TILAuthor.dummy(),
      createdAt = LocalDateTime.now().toString(),
    )
  }
}
