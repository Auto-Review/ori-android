package com.dd2d.data_source.remote.server._common

import kotlinx.serialization.Serializable

@Serializable
data class PagingResponseDto<I>(
  val dtoList: List<I>,
  val totalPage: Int
)

@Serializable
data class CommentPagingResponseDto<I>(
  val commentList: List<I>,
  val totalPage: Int
)