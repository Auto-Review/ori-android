package com.dd2d.presentation.my.conent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.list.RefreshLazyColumn
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.presentation.my.component.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyCodePostListContent(
    listState: RefreshLazyListState,
    list: SnapshotStateList<CodePostListItem>,
    requestNextPage: () -> Unit,
    requestRefresh: () -> Unit,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        RefreshLazyColumn(
            onRefresh = requestRefresh,
            onNextPage = requestNextPage,
            isLoading = listState is RefreshLazyListState.Loading,
            isRefreshing = listState is RefreshLazyListState.Refreshing,
            contentPadding = PaddingValues(horizontal = 27.dp, vertical = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = list,
                key = CodePostListItem::id
            ) { item ->
                CodePostListItemComponent(
                    item = item,
                    onClick = { onItemClick(item.id) },
                    modifier = Modifier.fillMaxWidth().animateItem()
                )
                HorizontalDivider()
            }
        }
    }
}


@Preview
@Preview(locale = "ko")
@Composable
private fun MyCodeListContentPrev() {
    AppTheme {
        MyCodePostListContent(
            listState = RefreshLazyListState.Success,
            list = List(30) { CodePostListItem.dummy(it) }.toMutableStateList(),
            requestNextPage = {},
            requestRefresh = {},
            onItemClick = {},
            modifier = Modifier
        )
    }
}