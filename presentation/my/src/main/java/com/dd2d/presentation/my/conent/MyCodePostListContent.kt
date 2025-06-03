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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.presentation.my.component.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyCodePostListContent(
    listController: LazyListController<CodePostListOptions, CodePostListItem>,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        com.dd2d.core.presentation.list.v2.RefreshLazyColumn(
            controller = listController,
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = listController.list,
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
    val list = remember {
        Pagination(
            list = List(30) {
                CodePostListItem.dummy(it)
            },
            currentPage = 1,
            totalPage = 1,
            totalItemCount = 30,
        )
    }
    AppTheme {
        MyCodePostListContent(
            listController = LazyListController(
                option = CodePostListOptions(),
                scope = rememberCoroutineScope(),
                getList = {
                    Result.success(list)
                }
            ),
            onItemClick = {},
            modifier = Modifier
        )
    }
}