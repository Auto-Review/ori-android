package com.dd2d.presentation.code_post.list.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.presentation.code_post.list.content.CodePostListScreenContent
import com.dd2d.presentation.code_post.list.view_model.CodePostListViewModel

@Composable
fun CodePostListScreen(
    onCodePostClick: (id: Int) ->Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<CodePostListViewModel>()
    val listState by viewModel.codePostListManager.state.collectAsStateWithLifecycle()

    var exception by remember { mutableStateOf<ManagedException?>(null) }

    LaunchedEffect(Unit) {
        viewModel.onRefresh()
    }

    LaunchedEffect(key1 = listState) {
        exception = (listState as? RefreshLazyListState.Error)?.exception
    }

    Scaffold(modifier = modifier.background(Color.Cyan)) { inner ->
        CodePostListScreenContent(
            state = listState,
            list = viewModel.codePostListManager.list,
            requestNextPage = viewModel::onNextPage,
            requestRefresh = viewModel::onRefresh,
            onDetailClick = onCodePostClick,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }

    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::onRefresh)
    }
}