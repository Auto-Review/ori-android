package com.dd2d.presentation.search.list.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.main_tab.MainPagerTab
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.domain.til.model.TILListItem
import com.dd2d.domain.til.model.TILListOptions

@Composable
internal fun SearchScreenContent(
  codePostListController: LazyListController<CodePostListOptions, CodePostListItem>,
  tilPostListController: LazyListController<TILListOptions, TILListItem>,
  onCodePostDetailClick: (codePostId: Int) -> Unit,
  onTILDetailClick: (tilId: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val tabs = rememberSaveable { listOf("CODE", "TIL") }
  val pagerState = rememberPagerState { tabs.size }
  MainPagerTab(
    pagerState = pagerState,
    tabs = tabs,
    modifier = modifier.fillMaxSize(),
  ) { page ->
    when(page) {
      0 -> {
        CodePostSearchListContent(
          listController = codePostListController,
          onDetailClick = onCodePostDetailClick,
          modifier = Modifier.fillMaxSize(),
        )
      }
      1 -> TILSearchListContent(
        listController = tilPostListController,
        onDetailClick = onTILDetailClick,
        modifier = Modifier.fillMaxSize(),
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenContentPrev() {
  fun getCodePost(option: CodePostListOptions): Result<Pagination<CodePostListItem>> {
    val list = List(option.take) {
      CodePostListItem.dummy(id = option.take * option.page + it)
    }
    return Result.success(Pagination(list = list, 0, 0, 0))
  }

  fun getTIL(option: TILListOptions): Result<Pagination<TILListItem>> {
    val list = List(option.take) {
      TILListItem.dummy(id = option.take * option.page + it)
    }
    return Result.success(Pagination(list = list, 0, 0, 0))
  }

  AppTheme {
    SearchScreenContent(
      codePostListController = LazyListController(
        option = CodePostListOptions(),
        scope = rememberCoroutineScope(),
        getList = ::getCodePost
      ),
      tilPostListController = LazyListController(
        option = TILListOptions(),
        scope = rememberCoroutineScope(),
        getList = ::getTIL
      ),
      onCodePostDetailClick = {},
      onTILDetailClick = {},
      modifier = Modifier
    )
  }
}