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
import com.dd2d.core.presentation.app_bar.PostTapBar
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.state.Stateful
import com.dd2d.domain.til.model.TIL
import com.example.presentation.til.detail.content.TILScreenContent
import com.example.presentation.til.detail.view_model.TILViewModel

@Composable
fun TILScreen(
    onBack: () ->Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<TILViewModel>()
    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val tilState by viewModel.tilState.collectAsStateWithLifecycle()
    val isAuthor by viewModel.isAuthor.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PostTapBar(
                title = (tilState as? Stateful.Success<TIL>)?.data?.title ?: "",
                onBack = onBack,
                isScrapped = viewModel.isScrapped,
                toggleScrap = viewModel::scrap,
                isAuthor = isAuthor,
                onUpdateClick = {},
                onDeleteClick = {},
            )
        },
        modifier = modifier
    ) { inner ->
        when(tilState) {
            is Stateful.Loading -> LoadingDialog()
            is Stateful.Error -> {
                ErrorDialog(
                    throwable = (tilState as Stateful.Error).cause,
                    onConfirm = onBack
                )
            }
            is Stateful.Success -> {
                TILScreenContent(
                    til = (tilState as Stateful.Success).data,
                    modifier = Modifier
                        .consumeWindowInsets(inner)
                        .fillMaxSize()
                        .padding(inner)
                )
            }
        }
    }
}