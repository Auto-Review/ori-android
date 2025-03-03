package com.dd2d.presentation.my.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.presentation.icon.AddIcon
import com.dd2d.core.presentation.option_selector.OptionSelector
import com.dd2d.presentation.my._navigation.MyPageNavigator
import com.dd2d.presentation.my.conent.MyCodePostListContent
import com.dd2d.presentation.my.conent.MyPageScreenContent
import com.dd2d.presentation.my.view_model.MyPageViewModel

@Composable
fun MyPageScreen(
    navigationEvent: (MyPageNavigator) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<MyPageViewModel>()

    val userState by viewModel.userState.collectAsStateWithLifecycle()
    val codePostListState by viewModel.codePostListManager.state.collectAsStateWithLifecycle()
    val tilListState by viewModel.tilListManager.state.collectAsStateWithLifecycle()

    var openFABMenu by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            OptionSelector(
                open = openFABMenu,
                close = { openFABMenu = !openFABMenu },
                options = listOf("CODE", "TIL"),
                onOptionSelected = { index ->
                    when(index) {
                        0 -> navigationEvent(MyPageNavigator.CodePostCreate)
                        1 -> navigationEvent(MyPageNavigator.TILPostCreate)
                    }
                },
            ) {
                FloatingActionButton(
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { openFABMenu = !openFABMenu },
                ) {
                    AddIcon()
                }
            }
        },
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
                    onItemClick = { id -> navigationEvent(MyPageNavigator.CodePost(id)) },
                    modifier = Modifier.fillMaxSize(),
                )
            },
            tilListContent = {

            },
            modifier = Modifier
                .consumeWindowInsets(inner)
                .fillMaxSize()
                .padding(inner)
        )
    }
}