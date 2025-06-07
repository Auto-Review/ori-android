package com.dd2d.domain.code_post.repository

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentReplyListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater
import kotlinx.coroutines.flow.Flow

interface CodePostCommentRepository {
  fun getCodePostCommentList(option: CodePostCommentListOption): Flow<DataState<Pagination<CodePostCommentListItem>>>
  fun getCodePostCommentReplyList(option: CodePostCommentReplyListOption): Flow<DataState<Pagination<CodePostCommentListItem>>>
  fun createCodePostComment(creator: CodePostCommentCreator): Flow<DataState<Int>>
  fun updateCodePostComment(updater: CodePostCommentUpdater): Flow<DataState<Int>>
  fun deleteCodePostComment(deleter: CodePostCommentDeleter): Flow<DataState<Unit>>
}