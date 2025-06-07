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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation._ori.ListFilter
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.list.v2.RefreshLazyColumn
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.presentation.code_post.list.component.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun CodePostListScreenContent(
  listController: LazyListController<CodePostListOptions, CodePostListItem>,
  onDetailClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  RefreshLazyColumn(
    controller = listController,
    modifier = modifier
  ) {
    stickyHeader(key = "filter") {
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.background)
          .padding(horizontal = 24.dp, vertical = 12.dp)
      ) {
        ListFilter(
          currentValue = listController.option.language?.label ?: "모든 언어",
          values = listOf("모든 언어") + Code.Language.entries.map(Code.Language::label),
          onValueClick = { index ->
            listController.updateOption { prev ->
              prev.copy(language = Code.Language.entries.getOrNull(index - 1))
            }
          },
        )
        ListFilter(
          currentValue = listController.option.sort.label,
          values = CodePostListOptions.Sort.entries.map(CodePostListOptions.Sort::label),
          onValueClick = { index ->
            listController.updateOption { prev ->
              prev.copy(sort = CodePostListOptions.Sort.entries[index])
            }
          },
        )
      }
    }

    items(
      items = listController.list,
      key = CodePostListItem::id
    ) { item ->
      CodePostListItemComponent(
        codePost = item,
        onClick = { onDetailClick(item.id) },
        modifier = Modifier
          .fillMaxWidth()
          .animateItem(),
      )
      HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
  }
}

@Preview(showBackground = true)
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
        listController = LazyListController(
          option = CodePostListOptions(),
          scope = rememberCoroutineScope(),
          getList = {
            Result.success(Pagination(emptyList(), 1, 10, 100))
          },
        ),
        onDetailClick = {},
        modifier = Modifier
      )
    }
  }
}