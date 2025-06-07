package com.dd2d.data_source.remote.server.code_post.dto.request


import kotlinx.serialization.Serializable

@Serializable
data class CodePostReviewCreateRequestDto(
  val codePostId: Int,
  val code: String,
  val description: String
)