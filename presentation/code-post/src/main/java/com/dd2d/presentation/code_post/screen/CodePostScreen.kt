package com.dd2d.presentation.code_post.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.dialog.ConfirmDialog
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.code_post.R
import com.dd2d.presentation.code_post.content.detail.CodePostScreenContent
import com.dd2d.presentation.code_post.content.detail.CodePostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val viewModel = hiltViewModel<CodePostViewModel>()
    val codePostState by viewModel.codePostState.collectAsStateWithLifecycle()
    val deleteState by viewModel.uiState.collectAsStateWithLifecycle()

    var deleteException by remember { mutableStateOf<ManagedException?>(null) }
    var openDeleteConfirmDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = deleteState) {
        deleteException = (deleteState as? UIState.Error)?.exception
        if(deleteState is UIState.Success) {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            CenterTitleTopBar(
                title = "",
                onBack = onBack,
                actions = {
                    IconButton(onClick = { openDeleteConfirmDialog = true }) {
                        VectorIcon(res = R.drawable.delete)
                    }
                }
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
                    codePost = (codePostState as DataState.Success).data,
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }

    deleteException?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::stateToIdle)
    }

    if(openDeleteConfirmDialog) {
        ConfirmDialog(
            title = "삭제하시겠습니까?",
            message = null,
            onCancel = { openDeleteConfirmDialog = false },
            onConfirm = {
                openDeleteConfirmDialog = false
                viewModel.deleteCodePost()
            }
        )
    }
}