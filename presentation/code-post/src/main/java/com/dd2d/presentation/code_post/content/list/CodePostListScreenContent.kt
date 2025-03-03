package com.dd2d.presentation.code_post.content.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.icon.AddIcon
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.list.RefreshLazyColumn
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.presentation.code_post.component.list.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CodePostListScreenContent(
    state: RefreshLazyListState,
    list: SnapshotStateList<CodePostListItem>,
    onClick: (id: Int) -> Unit,
    onNext: () -> Unit,
    onRefresh: () -> Unit,
    onCreate: () -> Unit,
    onSearch: (searchText: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyState = rememberLazyListState()

    val isFABExtend by remember {
        derivedStateOf {
            !lazyState.canScrollBackward
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                expanded = isFABExtend,
                onClick = onCreate,
                icon = { AddIcon() },
                text = { Main400Text(text = "글쓰기") },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            )
        },
        modifier = modifier
    ) { innerPadding ->
        RefreshLazyColumn(
            onNextPage = onNext,
            onRefresh = onRefresh,
            isLoading = state is RefreshLazyListState.Loading,
            isRefreshing = state is RefreshLazyListState.Refreshing,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            lazyState = lazyState,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item(key = "search") {
                SearchComponent(
                    onSearch = onSearch,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(
                items = list,
                key = CodePostListItem::id
            ) { item ->
                CodePostListItemComponent(
                    codePost = item,
                    onClick = { onClick(item.id) },
                    modifier = Modifier.fillMaxWidth().animateItem(),
                )
            }
        }
    }
}

@Composable
private fun SearchComponent(
    onSearch: (searchText: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf("") }

    val handleSearch: () -> Unit = {
        keyboard?.hide()
        onSearch(text)
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        shape = RoundedCornerShape(20.dp),
        trailingIcon = {
            IconButton(
                onClick = handleSearch
            ) {
                VectorIcon(icon = Icons.Default.Search)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions { handleSearch() },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            errorTextColor = MaterialTheme.colorScheme.onSurface,

            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            errorContainerColor = MaterialTheme.colorScheme.surface,

            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,

            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.error,

            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
            )
        ),
        modifier = modifier
    )
}

@Preview
@Preview(locale = "ko", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CodePostListScreenContentPrev() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CodePostListScreenContent(
                list = List(30) { CodePostListItem.dummy(id = it) }.toMutableStateList(),
                state = RefreshLazyListState.Success,
                onNext = {},
                onRefresh = {},
                onSearch = {},
                modifier = Modifier
            )
        }
    }
}