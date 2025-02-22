package com.dd2d.presentation.code_post.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.code_post.content.create.CodePostCreateScreenContent
import com.dd2d.presentation.code_post.content.create.CodePostCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostCreateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostCreateViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var exception by remember { mutableStateOf<ManagedException?>(null) }

    LaunchedEffect(uiState) {
        exception = (uiState as? UIState.Error)?.exception
        if(uiState is UIState.Success) {
            onBack()
        }
    }

    Scaffold(
        topBar = { CenterTitleTopBar(title = "글쓰기?", onBack = onBack) },
        modifier = modifier
    ) { inner ->
        CodePostCreateScreenContent(
            onSave = viewModel::create,
            isSaving = uiState is UIState.Loading,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }

    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::stateToIdle)
    }
}