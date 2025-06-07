package com.dd2d.domain.code_post.repository

import com.dd2d.core.core.state.DataState
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter
import com.dd2d.domain.code_post.model.review.CodePostReviewUpdater
import kotlinx.coroutines.flow.Flow

interface CodePostReviewRepository {
  fun getCodePostReviewList(codePostId: Int): Flow<DataState<List<CodePostReview>>>
  fun getCodePostReview(id: Int): Flow<DataState<CodePostReview>>
  fun createCodePostReview(creator: CodePostReviewCreator): Flow<DataState<Unit>>
  fun updateCodePostReview(updater: CodePostReviewUpdater): Flow<DataState<Unit>>
  fun deleteCodePostReview(deleter: CodePostReviewDeleter): Flow<DataState<Unit>>
}