package com.dd2d.data.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.code_post.mapper.comment.toCodePostCommentCreateRequestDto
import com.dd2d.data.code_post.mapper.comment.toCodePostCommentDeleteRequestDto
import com.dd2d.data.code_post.mapper.comment.toCodePostCommentListItem
import com.dd2d.data.code_post.mapper.comment.toCodePostCommentUpdateRequestDto
import com.dd2d.data_source.remote.server._common.toCommentPagination
import com.dd2d.data_source.remote.server.code_post.CodePostCommentApi
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostCommentListItemResponseDto
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentReplyListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodePostCommentRepositoryImpl @Inject constructor(
  private val codePostCommentApi: CodePostCommentApi
) : CodePostCommentRepository {
  override fun getCodePostCommentList(option: CodePostCommentListOption): Flow<DataState<Pagination<CodePostCommentListItem>>> =
    flow {
      val response = codePostCommentApi.getCodePostCommentList(
        codePostId = option.codePostId,
        page = option.page,
        size = option.size
      )
      emit(
        response.toCommentPagination(
          requestPage = option.page,
          mapper = CodePostCommentListItemResponseDto::toCodePostCommentListItem
        )
      )
    }.asDataState()

  override fun getCodePostCommentReplyList(option: CodePostCommentReplyListOption): Flow<DataState<Pagination<CodePostCommentListItem>>> =
    flow {
      val response = codePostCommentApi.getCodePostCommentReplyList(
        codePostId = option.codePostId,
        parentCommentId = option.parentCommentId,
        page = option.page,
        size = option.size
      )
      emit(
        response.toCommentPagination(
          requestPage = option.page,
          mapper = CodePostCommentListItemResponseDto::toCodePostCommentListItem
        )
      )
    }.asDataState()

  override fun createCodePostComment(creator: CodePostCommentCreator): Flow<DataState<Int>> = flow {
    val response = codePostCommentApi.createCodePostComment(
      creator = creator.toCodePostCommentCreateRequestDto()
    )
    emit(response)
  }.asDataState()

  override fun updateCodePostComment(updater: CodePostCommentUpdater): Flow<DataState<Int>> = flow {
    val response = codePostCommentApi.updateCodePostComment(
      updater = updater.toCodePostCommentUpdateRequestDto()
    )
    emit(response)
  }.asDataState()

  override fun deleteCodePostComment(deleter: CodePostCommentDeleter): Flow<DataState<Unit>> =
    flow {
      codePostCommentApi.deleteCodePostComment(
        deleter = deleter.toCodePostCommentDeleteRequestDto()
      )
      emit(Unit)
    }.asDataState()
}