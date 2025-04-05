package com.example.presentation.til.create.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.dd2d.core.presentation.dialog.ConfirmDialog
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.state.UIState
import com.example.presentation.til.create.component.TILScreenTopBar
import com.example.presentation.til.create.content.TILCreateScreenContent
import com.example.presentation.til.create.view_model.TILCreateViewModel

@Composable
fun TILCreateScreen(
    onBack: () ->Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<TILCreateViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var exception by remember { mutableStateOf<ManagedException?>(null) }
    var openCreateSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState) {
        exception = (uiState as? UIState.Error)?.exception
        openCreateSuccessDialog = uiState is UIState.Success
    }

    Scaffold(
        topBar = {
            TILScreenTopBar(
                onBack = onBack,
                onCreate = viewModel::create,
                isCreating = uiState is UIState.Loading
            )
        },
        modifier = modifier) { inner ->
        TILCreateScreenContent(
            createState = viewModel.createState,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }

    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::stateToIdle)
    }

    if(openCreateSuccessDialog) {
        ConfirmDialog(
            title = "게시물이 생성되었습니다.",
            message = null,
            onConfirm = {
                openCreateSuccessDialog = false
                onBack()
            }
        )
    }
}