package com.dd2d.data.code_post.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.asDataState
import com.dd2d.data.code_post.mapper.review.toCodePostReview
import com.dd2d.data.code_post.mapper.review.toCodePostReviewCreateRequestDto
import com.dd2d.data.code_post.mapper.review.toCodePostReviewDeleteRequestDto
import com.dd2d.data.code_post.mapper.review.toCodePostReviewUpdateRequestDto
import com.dd2d.data_source.remote.server.code_post.CodePostReviewApi
import com.dd2d.data_source.remote.server.code_post.dto.response.CodePostReviewResponseDto
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter
import com.dd2d.domain.code_post.model.review.CodePostReviewUpdater
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodePostReviewRepositoryImpl @Inject constructor(
  private val codePostReviewApi: CodePostReviewApi
) : CodePostReviewRepository {
  override fun getCodePostReviewList(codePostId: Int): Flow<DataState<List<CodePostReview>>> =
    flow {
      val response = codePostReviewApi.getCodePostReviewList(
        codePostId = codePostId
      )
      emit(response.map(CodePostReviewResponseDto::toCodePostReview))
    }.asDataState()

  override fun getCodePostReview(id: Int): Flow<DataState<CodePostReview>> = flow {
    val response = codePostReviewApi.getCodePostReview(id = id)
    emit(response.toCodePostReview())
  }.asDataState()

  override fun createCodePostReview(creator: CodePostReviewCreator): Flow<DataState<Unit>> = flow {
    val response = codePostReviewApi.createCodePostReview(
      creator = creator.toCodePostReviewCreateRequestDto()
    )
    emit(Unit)
  }.asDataState()

  override fun updateCodePostReview(updater: CodePostReviewUpdater): Flow<DataState<Unit>> = flow {
    val response = codePostReviewApi.updateCodePostReview(
      updater = updater.toCodePostReviewUpdateRequestDto()
    )
    emit(Unit)
  }.asDataState()

  override fun deleteCodePostReview(deleter: CodePostReviewDeleter): Flow<DataState<Unit>> = flow {
    codePostReviewApi.deleteCodePostReview(
      deleter = deleter.toCodePostReviewDeleteRequestDto()
    )
    emit(Unit)
  }.asDataState()
}