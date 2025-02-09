package com.dd2d.presentation.code_post.content.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.list.RefreshLazyColumn
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.presentation.code_post.component.list.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CodePostListScreenContent(
    list: SnapshotStateList<CodePostListItem>,
    onClick: (id: Int) -> Unit,
    state: RefreshLazyListState,
    onNext: () -> Unit,
    onRefresh: () -> Unit,
    onSearch: (searchText: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier
    ) { 
        RefreshLazyColumn(
            onNextPage = onNext,
            onRefresh = onRefresh,
            isLoading = state is RefreshLazyListState.Loading,
            isRefreshing = state is RefreshLazyListState.Refreshing,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
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
                    modifier = Modifier.fillMaxWidth(),
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
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        shape = RoundedCornerShape(20.dp),
        trailingIcon = {
            IconButton(
                onClick = { onSearch(text) }
            ) {
                VectorIcon(icon = Icons.Default.Search)
            }
        },
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
                list = List(30) { CodePostListItem.dummy.copy(id = it) }.toMutableStateList(),
                onClick = {},
                state = RefreshLazyListState.Success,
                onNext = {},
                onRefresh = {},
                onSearch = {},
                modifier = Modifier
            )
        }
    }
}