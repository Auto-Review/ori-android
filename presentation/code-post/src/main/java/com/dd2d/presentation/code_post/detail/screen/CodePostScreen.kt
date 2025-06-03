package com.dd2d.presentation.code_post.detail.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.action.CommonActionResult
import com.dd2d.core.presentation.app_bar.PostTapBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.message.rememberMessageHolder
import com.dd2d.core.presentation.scaffold.MessageHandlerScaffold
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.presentation.code_post.detail.content.CodePostScreenContent
import com.dd2d.presentation.code_post.detail.model.CodePostDeleteCancelResult
import com.dd2d.presentation.code_post.detail.model.CodePostDeleteSuccessResult
import com.dd2d.presentation.code_post.detail.view_model.CodePostViewModel

@Composable
fun CodePostScreen(
    onBack: () -> Unit,
    onReviewCreateClick: (codePostId: Int) -> Unit,
    onReviewUpdateClick: (codePostId: Int, reviewId: Int) -> Unit,
    onCodePostUpdateClick: (codePostId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostViewModel>()
    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val codePostState by viewModel.codePostState.collectAsStateWithLifecycle()
    val isAuthor by viewModel.isAuthor.collectAsStateWithLifecycle()
    val messageHolder = rememberMessageHolder()

    LaunchedEffect(key1 = Unit) {
        viewModel.actionBus.collectResult { result ->
            when(result) {
                is CommonActionResult.ActionFailure -> {
                    messageHolder.defaultDialogOf {
                        title = "게시물 삭제 실패"
                        content = result.exception.message
                    }
                }
                is CodePostDeleteSuccessResult -> {
                    messageHolder.dialogOf {
                        title = "게시물이 삭제되었습니다."
                        addAction {
                            text = "확인"
                            onClick = { dismissRequest ->
                                dismissRequest()
                                onBack()
                            }
                        }
                    }
                }
                is CodePostDeleteCancelResult -> {
                    messageHolder.snackbarOf {
                        content = "삭제를 취소했습니다."
                        onDismiss = {}
                    }
                }
            }
        }
    }

    MessageHandlerScaffold(
        messageHolder = messageHolder,
        topBar = {
            PostTapBar(
                title = (codePostState as? Stateful.Success)?.data?.title ?: "",
                onBack = onBack,
                toggleScrap = viewModel::toggleScrap,
                isScrapped = viewModel.isScrapped,
                isAuthor = isAuthor,
                onUpdateClick = { onCodePostUpdateClick(viewModel.route.id) },
                onDeleteClick = {
                    messageHolder.dialogOf {
                        title = "삭제하시겠습니까?"
                        addAction {
                            text = "취소"
                            textColor = Color.Gray
                        }
                        addAction {
                            text = "삭제"
                            textColor = Color.Red
                            onClick = { dismissRequest ->
                                dismissRequest()
                                messageHolder.snackbarOf {
                                    content = "삭제중"
                                    actionLabel = "취소"
                                    action = viewModel::cancelDelete
                                }
                                viewModel.deleteCodePost()
                            }
                        }
                    }
                },
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when(codePostState) {
            is Stateful.Loading -> LoadingDialog()
            is Stateful.Error -> {
                ErrorDialog(
                    throwable = (codePostState as Stateful.Error).cause,
                    onConfirm = onBack
                )
            }
            is Stateful.Success -> {
                CodePostScreenContent(
                    user = (userState as? DataState.Success)?.data,
                    codePost = (codePostState as Stateful.Success).data,
                    reviewStateHolder = viewModel.reviewStateHolder,
                    onReviewCreateClick = { onReviewCreateClick(viewModel.route.id) },
                    onReviewUpdateClick = { reviewId -> onReviewUpdateClick(viewModel.route.id, reviewId) },
                    commentStateHolder = viewModel.commentStateHolder,
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}