package com.dd2d.presentation.my.conent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.list.v2.RefreshLazyColumn
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions
import com.dd2d.presentation.my.component.TILListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyTILListContent(
    listController: LazyListController<TILListOptions, TILListItem>,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RefreshLazyColumn(
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
            HorizontalDivider()
        }
    }
}