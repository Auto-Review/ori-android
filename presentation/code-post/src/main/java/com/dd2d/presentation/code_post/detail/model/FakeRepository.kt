package com.dd2d.presentation.code_post.detail.model

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentReplyListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.domain.code_post.model.post.CodePostCreator
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.code_post.model.post.CodePostUpdater
import com.dd2d.domain.code_post.model.review.CodePostReview
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator
import com.dd2d.domain.code_post.model.review.CodePostReviewDeleter
import com.dd2d.domain.code_post.model.review.CodePostReviewUpdater
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.domain.code_post.repository.CodePostReviewRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class FakeReviewRepository: CodePostReviewRepository {
    override fun getCodePostReviewList(codePostId: Int): Flow<DataState<List<CodePostReview>>> {
        return flowOf(DataState.Success(List(3) { CodePostReview.dummy(it) }))
    }

    override fun getCodePostReview(id: Int): Flow<DataState<CodePostReview>> {
        return emptyFlow()
    }

    override fun createCodePostReview(creator: CodePostReviewCreator): Flow<DataState<Int>> {
        return emptyFlow()
    }

    override fun updateCodePostReview(updater: CodePostReviewUpdater): Flow<DataState<Int>> {
        return emptyFlow()
    }

    override fun deleteCodePostReview(deleter: CodePostReviewDeleter): Flow<DataState<Unit>> {
        return flow {
            delay(500)
            emit(DataState.Success(Unit))
        }
    }
}

internal class FakeCommentRepository: CodePostCommentRepository {
    override fun getCodePostCommentList(option: CodePostCommentListOption): Flow<DataState<Pagination<CodePostCommentListItem>>> {
        val list = List(10) { CodePostCommentListItem.dummy(it) }
        val p = Pagination(
            list = list,
            currentPage = 0,
            totalPage = 3,
            totalItemCount = 123,
        )
        return flowOf(DataState.Success(p))
    }

    override fun getCodePostCommentReplyList(option: CodePostCommentReplyListOption): Flow<DataState<Pagination<CodePostCommentListItem>>> {
        return emptyFlow()
    }

    override fun createCodePostComment(creator: CodePostCommentCreator): Flow<DataState<Int>> {
        return emptyFlow()
    }

    override fun updateCodePostComment(updater: CodePostCommentUpdater): Flow<DataState<Int>> {
        return emptyFlow()
    }

    override fun deleteCodePostComment(deleter: CodePostCommentDeleter): Flow<DataState<Unit>> {
        return emptyFlow()
    }
}

internal class FakeCodePostRepository: CodePostRepository {
    override fun getCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> {
        return emptyFlow()
    }

    override fun getMyCodePostList(options: CodePostListOptions): Flow<DataState<Pagination<CodePostListItem>>> {
        return emptyFlow()
    }

    override fun getCodePost(id: Int): Flow<DataState<CodePost>> {
        return flow {
            emit(DataState.Success(CodePost.dummy()))
        }
    }

    override fun createCodePost(create: CodePostCreator): Flow<DataState<Int>> {
        return emptyFlow()
    }

    override fun updateCodePost(update: CodePostUpdater): Flow<DataState<Boolean>> {
        return emptyFlow()
    }

    override fun deleteCodePost(id: Int): Flow<DataState<Boolean>> {
        return emptyFlow()
    }
}