package com.example.presentation.til.detail.screen

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
import com.example.presentation.til.detail.content.TILScreenContent
import com.example.presentation.til.detail.view_model.TILViewModel

@Composable
fun TILScreen(
    onBack: () ->Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<TILViewModel>()
    val tilState by viewModel.tilState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PostTapBar(
                title = (tilState as? DataState.Success)?.data?.title ?: "",
                onBack = onBack,
                isScrapped = viewModel.isScrapped,
                toggleScrap = viewModel::scrap,
                isAuthor = false,
                onUpdateClick = {},
                onDeleteClick = {},
            )
        },
        modifier = modifier
    ) { inner ->
        when(tilState) {
            is DataState.Loading -> LoadingDialog()
            is DataState.Error -> {
                ErrorDialog(
                    exception = (tilState as DataState.Error).exception,
                    onConfirm = onBack
                )
            }
            is DataState.Success -> {
                TILScreenContent(
                    til = (tilState as DataState.Success).data,
                    modifier = Modifier
                        .consumeWindowInsets(inner)
                        .fillMaxSize()
                        .padding(inner)
                )
            }
        }
    }
}