package com.dd2d.presentation.code_post.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.presentation.code_post.content.list.CodePostListScreenContent
import com.dd2d.presentation.code_post.content.list.CodePostListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostListScreen(
    onCodePostClick: (id: Int) -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
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

    Scaffold(
        topBar = {
            CenterTitleTopBar(title = "smaple title", onBack = null)
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        CodePostListScreenContent(
            state = listState,
            list = viewModel.codePostListManager.list,
            onNext = viewModel::onNextPage,
            onRefresh = viewModel::onRefresh,
            onClick = onCodePostClick,
            onCreate = onCreateClick,
            onSearch = viewModel::search,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        )
    }

    exception?.let { e ->
        ErrorDialog(exception = e, onConfirm = viewModel::onRefresh)
    }
}