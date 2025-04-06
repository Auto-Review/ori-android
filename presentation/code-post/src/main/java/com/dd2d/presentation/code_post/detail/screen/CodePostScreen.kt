package com.dd2d.presentation.code_post.detail.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.app_bar.PostTapBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.presentation.code_post.detail.content.CodePostScreenContent
import com.dd2d.presentation.code_post.detail.view_model.CodePostViewModel

@Composable
fun CodePostScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostViewModel>()
    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val codePostState by viewModel.codePostStateHolder.codePostState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PostTapBar(
                title = (codePostState as? DataState.Success)?.data?.title ?: "",
                onBack = onBack,
                onScrap = viewModel.codePostStateHolder::scrap,
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
                    codePostStateHolder = viewModel.codePostStateHolder,
                    reviewStateHolder = viewModel.reviewStateHolder,
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