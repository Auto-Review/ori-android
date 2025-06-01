package com.dd2d.presentation.code_post.detail.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.app_bar.PostTapBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.message.MessageHandler
import com.dd2d.core.presentation.message.rememberMessageHolder
import com.dd2d.core.presentation.state.ActionResult
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.presentation.code_post.detail.content.CodePostScreenContent
import com.dd2d.presentation.code_post.detail.view_model.CodePostViewModel

@Composable
fun CodePostScreen(
    onBack: () -> Unit,
    onReviewCreateClick: (codePostId: Int) -> Unit,
    onReviewUpdateClick: (codePostId: Int, reviewId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostViewModel>()
    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val codePostState by viewModel.codePostState.collectAsStateWithLifecycle()
    val messageHolder = rememberMessageHolder()

    MessageHandler(messageHolder)

    LaunchedEffect(key1 = Unit) {
        viewModel.deleteResult.collect { result ->
            when(result) {
                is ActionResult.Failure -> {
                    messageHolder.defaultDialogOf {
                        title = "게시물 삭제 실패"
                        content = result.cause.message
                    }
                }
                is ActionResult.Success -> {
                    messageHolder.defaultDialogOf {
                        title = "게시물이 삭제되었습니다."
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            PostTapBar(
                title = (codePostState as? Stateful.Success)?.data?.title ?: "",
                onBack = onBack,
                toggleScrap = viewModel::toggleScrap,
                isScrapped = viewModel.isScrapped,
                onDelete = if(viewModel.canDelete) viewModel::deleteCodePost else null,
                isDeleting = viewModel.isDeleting,
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