@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.dd2d.presentation.search.list.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.ListFilter
import com.dd2d.core.presentation.list.item.PostListItemComponent
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.list.v2.RefreshLazyColumn
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions

@Composable
internal fun CodePostSearchListContent(
  listController: LazyListController<CodePostListOptions, CodePostListItem>,
  onDetailClick: (codePostId: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  RefreshLazyColumn(
    controller = listController,
    modifier = modifier
      .fillMaxSize()
  ) {
    stickyHeader(key = "filter") {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.White)
          .padding(horizontal = 24.dp, vertical = 8.dp)
      ) {
        ListFilter(
          currentValue = listController.option.sort.label,
          values = CodePostListOptions.Sort.entries.map(CodePostListOptions.Sort::label),
          onValueClick = { index ->
            listController.updateOption { prev ->
              prev.copy(sort = CodePostListOptions.Sort.entries[index])
            }
          },
        )

        ListFilter(
          currentValue = listController.option.language?.label?: "모든 언어",
          values = listOf("모든 언어") + Code.Language.entries.map(Code.Language::label),
          onValueClick = { index ->
            listController.updateOption { prev ->
              prev.copy(language = Code.Language.entries.getOrNull(index-1))
            }
          },
        )
      }
    }

    items(
      items = listController.list,
      key = CodePostListItem::id
    ) { item ->
      PostListItemComponent(
        title = item.title,
        authorName = item.author.nickname,
        createdAt = item.createdAt,
        commentCount = item.commentCount,
        onClick = { onDetailClick(item.id) },
      )
    }
  }
}