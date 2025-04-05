package com.example.presentation.til.list.screen

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
import com.example.presentation.til.list.content.TILListScreenContent
import com.example.presentation.til.list.view_model.TILListViewModel

@Composable
fun TILListScreen(
    onTILClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<TILListViewModel>()
    val listState by viewModel.listManager.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Scaffold { inner ->
        TILListScreenContent(
            listState = listState,
            list = viewModel.listManager.list,
            requestRefresh = viewModel::refresh,
            requestNextPage = viewModel::nextPage,
            onItemClick = onTILClick,
            modifier = modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }
}