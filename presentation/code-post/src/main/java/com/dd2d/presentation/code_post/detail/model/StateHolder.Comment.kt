package com.dd2d.presentation.code_post.detail.model

import com.dd2d.core.core.model.Pagination
import com.dd2d.core.core.state.DataState
import com.dd2d.core.core.state.onEachState
import com.dd2d.core.presentation.list.RefreshLazyListManager
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.core.presentation.state.stateToError
import com.dd2d.core.presentation.state.stateToLoading
import com.dd2d.core.presentation.state.stateToSuccess
import com.dd2d.domain.code_post.model.comment.CodePostCommentCreator
import com.dd2d.domain.code_post.model.comment.CodePostCommentDeleter
import com.dd2d.domain.code_post.model.comment.CodePostCommentListItem
import com.dd2d.domain.code_post.model.comment.CodePostCommentListOption
import com.dd2d.domain.code_post.model.comment.CodePostCommentUpdater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn

internal class CommentStateHolder(
  id: Int,
  private val scope: CoroutineScope,
  getCommentListFlow: (option: CodePostCommentListOption) -> Flow<DataState<Pagination<CodePostCommentListItem>>>,
  private val createCommentFlow: (option: CodePostCommentCreator) -> Flow<DataState<Int>>,
  private val updateCommentFlow: (option: CodePostCommentUpdater) -> Flow<DataState<Int>>,
  private val deleteCommentFlow: (deleter: CodePostCommentDeleter) -> Flow<DataState<Unit>>,
) : UIStateManager {
  override val uiState = MutableStateFlow<UIState>(UIState.Idle)

  val commentListManager = RefreshLazyListManager(
    initialListOption = CodePostCommentListOption(codePostId = id),
    scope = scope,
    flow = getCommentListFlow,
  )

  fun pageTo(page: Int) = with(commentListManager) { refresh(options = options.copy(page = page)) }
  private fun refresh() = with(commentListManager) { refresh(options) }

  val inputState = CommentInputState(codePostId = id)
  fun commitComment() {
    val flow = inputState.updateCommentId
      ?.let { updateCommentFlow(inputState.toUpdater()) }
      ?: run { createCommentFlow(inputState.toCreator()) }

    flow.onEachState(
      onLoading = { uiState.stateToLoading() },
      onError = { uiState.stateToError(it) },
      onSuccess = {
        inputState.reset()
        refresh()
        uiState.stateToSuccess()
      },
    ).launchIn(scope = scope)
  }

  fun deleteComment(commentId: Int, authorId: Int) {
    deleteCommentFlow(CodePostCommentDeleter(commentId, authorId))
      .onEachState(
        onLoading = { uiState.stateToLoading() },
        onError = { uiState.stateToError(it) },
        onSuccess = {
          commentListManager.list.removeIf { comment -> comment.id == commentId }
          uiState.stateToSuccess()
        },
      )
      .launchIn(scope)
  }

  fun report() {

  }
}
