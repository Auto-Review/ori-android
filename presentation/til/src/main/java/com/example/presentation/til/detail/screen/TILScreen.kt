package com.example.presentation.til.detail.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.dialog.ErrorDialog
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.icon.BookmarkIcon
import com.dd2d.core.presentation.main_text.Main700Text
import com.example.presentation.til.detail.content.TILScreenContent
import com.example.presentation.til.detail.view_model.TILViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TILScreen(
    onBack: () ->Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<TILViewModel>()
    val tilState by viewModel.tilState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Main700Text(
                        text = (tilState as? DataState.Success)?.data?.title?: "",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        BackIcon()
                    }
                },
                actions = {
                    IconButton(onClick = viewModel::scrap) {
                        BookmarkIcon(onBookMark = listOf(true, false).random())
                    }
                },
                modifier = Modifier.fillMaxWidth(),
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