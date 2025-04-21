package com.dd2d.presentation.code_post.list.content

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.list.RefreshLazyColumn
import com.dd2d.core.presentation.list.RefreshLazyListState
import com.dd2d.core.presentation.ori.ListFilter
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.presentation.code_post.list.component.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun CodePostListScreenContent(
    state: RefreshLazyListState,
    list: SnapshotStateList<CodePostListItem>,
    requestNextPage: () -> Unit,
    requestRefresh: () -> Unit,
    onDetailClick: (id: Int) ->Unit,
    modifier: Modifier = Modifier
) {
    val dummyFilter1 = remember { listOf("모든 언어", "kotlin", "java", "swift") }
    val dummyFilter2 = remember { listOf("최신순", "오래된순") }

    var currentFilter1 by remember { mutableStateOf(dummyFilter1[0]) }
    var currentFilter2 by remember { mutableStateOf(dummyFilter2[0]) }

    RefreshLazyColumn(
        onNextPage = requestNextPage,
        onRefresh = requestRefresh,
        isLoading = state is RefreshLazyListState.Loading,
        isRefreshing = state is RefreshLazyListState.Refreshing,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        stickyHeader(key = "filter") {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 12.dp)
            ) {
                ListFilter(
                    currentValue = currentFilter1,
                    values = dummyFilter1,
                    onValueClick = { index -> currentFilter1 = dummyFilter1[index] },
                )
                ListFilter(
                    currentValue = currentFilter2,
                    values = dummyFilter2,
                    onValueClick = { index -> currentFilter2 = dummyFilter2[index] },
                )
            }
        }
        items(
            items = list,
            key = CodePostListItem::id
        ) { item ->
            CodePostListItemComponent(
                codePost = item,
                onClick = { onDetailClick(item.id) },
                modifier = Modifier.fillMaxWidth().animateItem(),
            )
        }
    }
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
                requestNextPage = {},
                requestRefresh = {},
                onDetailClick = {},
                modifier = Modifier
            )
        }
    }
}