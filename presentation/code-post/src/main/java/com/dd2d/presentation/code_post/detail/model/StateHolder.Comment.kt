package com.dd2d.presentation.code_post.detail.model

import android.util.Log
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.list.RefreshLazyListManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class CommentStateHolder(
    private val id: Int,
    private val scope: CoroutineScope,
    getCommentListFlow: (option: CodePostCommentListOption) -> Flow<DataState<Pagination<CodePostCommentListItem>>>,
    private val createCommentFlow: (option: CodePostCommentCreator) -> Flow<DataState<Int>>,
    private val deleteCommentFlow: (deleter: CodePostCommentDeleter) -> Flow<DataState<Unit>>,
) {
    val commentListManager = RefreshLazyListManager(
        initialListOption = CodePostCommentListOption(codePostId = id),
        scope = scope,
        flow = { getCommentListFlow(it).onEach { Log.d("LOG_CHECK", "$it: ") } },
    )
    fun nextPage() = with(commentListManager) { loadMore(options = options.copy(page = options.page + 1)) }
    fun refresh() = with(commentListManager) { refresh(options = CodePostCommentListOption(codePostId = id)) }

    val createState = CommentCreateState()
    fun registerComment() {
        createCommentFlow(createState.toCreator(codePostId = id))
            .onEachState(
                onLoading = { createState.uiState.stateToLoading() },
                onError = { createState.uiState.stateToError(it) },
                onSuccess = { createState.uiState.stateToSuccess() },
            )
            .launchIn(scope = scope)
    }

    fun deleteComment(commentId: Int, authorId: Int) {
        deleteCommentFlow(CodePostCommentDeleter(commentId, authorId)).launchIn(scope)
    }

    fun report() {

    }
}
