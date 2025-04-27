package com.dd2d.presentation.code_post.review.screen

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
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.code_post.review.component.CodePostReviewCreateScreenTopBar
import com.dd2d.presentation.code_post.review.content.CodePostReviewCreateScreenContent
import com.dd2d.presentation.code_post.review.view_model.CodePostReviewCreateViewModel

@Composable
fun CodePostReviewCreateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CodePostReviewCreateViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var exception by remember { mutableStateOf<ManagedException?>(null) }

    LaunchedEffect(key1 = uiState) {
        exception = (uiState as? UIState.Error)?.exception
    }

    Scaffold(
        topBar = { CodePostReviewCreateScreenTopBar(viewModel = viewModel, uiState = uiState, onBack = onBack) },
        modifier = modifier
    ) { inner ->
        CodePostReviewCreateScreenContent(
            inputState = viewModel.inputState,
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