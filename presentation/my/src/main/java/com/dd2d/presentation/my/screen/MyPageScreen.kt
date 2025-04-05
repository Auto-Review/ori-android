package com.dd2d.presentation.my.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.presentation.my.conent.MyCodePostListContent
import com.dd2d.presentation.my.conent.MyPageScreenContent
import com.dd2d.presentation.my.conent.MyTILListContent
import com.dd2d.presentation.my.model.MyPageNavigateEvent
import com.dd2d.presentation.my.view_model.MyPageViewModel

@Composable
fun MyPageScreen(
    navigationEvent: (MyPageNavigateEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<MyPageViewModel>()

    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val codePostListState by viewModel.codePostListManager.state.collectAsStateWithLifecycle()
    val tilListState by viewModel.tilListManager.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.refreshCodePostList()
        viewModel.refreshTILList()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ){ inner ->
        MyPageScreenContent(
            userState = userState,
            codePostListContent = {
                MyCodePostListContent(
                    listState = codePostListState,
                    list = viewModel.codePostListManager.list,
                    requestNextPage = viewModel::nextCodePostPage,
                    requestRefresh = viewModel::refreshCodePostList,
                    onItemClick = { id -> navigationEvent(MyPageNavigateEvent.CodePost(id)) },
                    modifier = Modifier.fillMaxSize(),
                )
            },
            tilListContent = {
                MyTILListContent(
                    listState = tilListState,
                    list = viewModel.tilListManager.list,
                    requestNextPage = viewModel::nextTILPage,
                    requestRefresh = viewModel::refreshTILList,
                    onItemClick = { id -> navigationEvent(MyPageNavigateEvent.TIL(id)) },
                    modifier = Modifier.fillMaxSize(),
                )
            },
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }
}