package com.dd2d.presentation.code_post.detail.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.app_bar.PostTapBar
import com.dd2d.core.presentation.dialog.ConfirmDialog
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.state.UIState
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

    var exception by remember { mutableStateOf<ManagedException?>(null) }

    val deleteState by viewModel.deleteState.collectAsStateWithLifecycle()
    val canDelete by remember(userState, codePostState) {
        val userId = (userState as? DataState.Success)?.data?.id
        val codePostAuthorId = (codePostState as? DataState.Success)?.data?.author?.id

        derivedStateOf { userId == codePostAuthorId }
    }
    var openDeleteSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(deleteState) {
        openDeleteSuccessDialog = deleteState is UIState.Success
        exception = (deleteState as? UIState.Error)?.exception
    }

    Scaffold(
        topBar = {
            PostTapBar(
                title = (codePostState as? DataState.Success)?.data?.title ?: "",
                onBack = onBack,
                onScrap = null,
                onDelete = if(canDelete) viewModel::deleteCodePost else null,
                isDeleting = deleteState is UIState.Loading,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when(codePostState) {
            is DataState.Loading -> LoadingDialog()
            is DataState.Error -> {
                ErrorDialog(
                    exception = (codePostState as DataState.Error).exception,
                    onConfirm = onBack
                )
            }
            is DataState.Success -> {
                CodePostScreenContent(
                    user = (userState as? DataState.Success)?.data,
                    codePost = (codePostState as DataState.Success).data,
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

    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = { exception = null })
    }

    if(openDeleteSuccessDialog) {
        ConfirmDialog(
            title = "삭제가 완료되었습니다.",
            message = null,
            onConfirm = {
                openDeleteSuccessDialog = false
                onBack()
            }
        )
    }
}