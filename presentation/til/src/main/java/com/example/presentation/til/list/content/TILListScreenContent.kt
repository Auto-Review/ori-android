package com.example.presentation.til.list.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions
import com.example.presentation.til.list.component.TILListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TILListScreenContent(
    listController: LazyListController<TILListOptions, TILListItem>,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    com.dd2d.core.presentation.list.v2.RefreshLazyColumn(
        controller = listController,
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = listController.list,
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
    val list = remember {
        List(40) {
            TILListItem.dummy(it)
        }
    }
    AppTheme {
        TILListScreenContent(
            listController = LazyListController(
                option = TILListOptions(),
                scope = rememberCoroutineScope(),
                getList = {
                    Result.success(Pagination(list = list, 0, 0, 0))
                },
            ),
            onItemClick = {},
            modifier = Modifier
        )
    }
}