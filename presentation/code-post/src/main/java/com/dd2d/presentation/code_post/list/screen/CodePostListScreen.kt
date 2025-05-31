package com.dd2d.presentation.code_post.list.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.presentation.code_post.list.content.CodePostListScreenContent
import com.dd2d.presentation.code_post.list.view_model.CodePostListViewModel

@Composable
fun CodePostListScreen(
    onCodePostClick: (id: Int) ->Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<CodePostListViewModel>()

    Scaffold(modifier = modifier) { inner ->
        CodePostListScreenContent(
            listController = viewModel.listController,
            onDetailClick = onCodePostClick,
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }
}