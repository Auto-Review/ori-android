package com.dd2d.domain.code_post.model.scrap

import com.dd2d.core.core.model.DateString

data class CodePostScrapListItem(
  val id: Int,
  val codePostId: Int,
  val codePostTitle: String,
  val commentCount: Int,
  val writer: String,
  val createdAt: DateString
) {
  companion object {
    fun dummy(id: Int = 1) = CodePostScrapListItem(
      id = id,
      codePostId = 1,
      codePostTitle = "title $id",
      commentCount = 213,
      writer = "writer $id",
      createdAt = "2025-05-12",
    )
  }
}