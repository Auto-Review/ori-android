package com.dd2d.data_source.remote.server._common

import com.dd2d.core.core.model.Pagination

fun <DTO, Model> PagingResponseDto<DTO>.toPagination(
  requestPage: Int,
  mapper: (DTO) -> Model
): Pagination<Model> {
  return Pagination(
    list = this.dtoList.map(mapper),
    currentPage = requestPage + 1,
    totalPage = this.totalPage,
    totalItemCount = -1,
  )
}

fun <DTO, Model> CommentPagingResponseDto<DTO>.toCommentPagination(
  requestPage: Int,
  mapper: (DTO) -> Model
): Pagination<Model> {
  return Pagination(
    list = this.commentList.map(mapper),
    currentPage = requestPage + 1,
    totalPage = this.totalPage,
    totalItemCount = -1,
  )
}