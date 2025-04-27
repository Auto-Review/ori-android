package com.example.presentation.til.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.ListFilter
import com.dd2d.core.presentation.list.RefreshLazyColumn
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.til.model.TILListItem
import com.example.presentation.til.list.component.TILListItemComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun TILListScreenContent(
    listState: RefreshLazyListState,
    list: SnapshotStateList<TILListItem>,
    requestRefresh: () -> Unit,
    requestNextPage: () -> Unit,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dummyFilter = remember { listOf("최신순", "오래된순") }
    var currentFilter by remember { mutableStateOf(dummyFilter[0]) }
    RefreshLazyColumn(
        onRefresh = requestRefresh,
        onNextPage = requestNextPage,
        isLoading = listState is RefreshLazyListState.Loading,
        isRefreshing = listState is RefreshLazyListState.Refreshing,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        stickyHeader(key = "filter") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 12.dp)
            ) {
                ListFilter(
                    currentValue = currentFilter,
                    values = dummyFilter,
                    onValueClick = { index -> currentFilter = dummyFilter[index] },
                )
            }
        }

        items(
            items = list,
            key = TILListItem::id
        ) { item ->
            TILListItemComponent(
                item = item,
                onClick = { onItemClick(item.id) },
                modifier = Modifier.fillMaxWidth().animateItem()
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TILListScreenContentPrev() {
    AppTheme {
        TILListScreenContent(
            listState = RefreshLazyListState.Success,
            list = List(30) { TILListItem.dummy(it) }.toMutableStateList(),
            requestRefresh = {},
            requestNextPage = {},
            onItemClick = {},
            modifier = Modifier
        )
    }
}