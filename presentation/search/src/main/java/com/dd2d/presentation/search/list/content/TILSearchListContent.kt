@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.dd2d.presentation.search.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation.list.item.PostListItemComponent
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.list.v2.RefreshLazyColumn
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions

@Composable
internal fun TILSearchListContent(
  listController: LazyListController<TILListOptions, TILListItem>,
  onDetailClick: (tilId: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  RefreshLazyColumn(
    controller = listController,
    modifier = modifier
      .fillMaxSize()
  ) {
    items(
      items = listController.list,
      key = TILListItem::id
    ) { item ->
      PostListItemComponent(
        title = item.title,
        authorName = item.author.nickname,
        createdAt = item.createdAt,
        commentCount = null,
        onClick = { onDetailClick(item.id) },
      )
    }
  }

}