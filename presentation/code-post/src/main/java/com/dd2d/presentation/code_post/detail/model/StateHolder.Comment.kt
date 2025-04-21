package com.dd2d.presentation.code_post.detail.model

import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.list.RefreshLazyListManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import com.dd2d.domain.code_post.repository.CodePostCommentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn

internal class CommentStateHolder(
    private val id: Int,
    private val scope: CoroutineScope,
    private val repository: CodePostCommentRepository,
) {
    val commentListManager = RefreshLazyListManager(
        initialListOption = CodePostCommentListOption(codePostId = id),
        scope = scope,
        flow = repository::getCodePostCommentList,
    )
    fun nextPage() = with(commentListManager) { loadMore(options = options.copy(page = options.page + 1)) }
    fun refresh() = with(commentListManager) { refresh(options = CodePostCommentListOption(codePostId = id)) }

    val createState = CommentCreateState()
    fun registerComment() {
        repository
            .createCodePostComment(creator = createState.toCreator(codePostId = id))
            .onEachState(
                onLoading = { createState.uiState.stateToLoading() },
                onError = { createState.uiState.stateToError(it) },
                onSuccess = { createState.uiState.stateToSuccess() },
            )
            .launchIn(scope = scope)
    }

    fun deleteComment(commentId: Int, authorId: Int) {
        repository
            .deleteCodePostComment(deleter = CodePostCommentDeleter(commentId, authorId))
            .launchIn(scope)
    }

    fun report() {

    }
}
