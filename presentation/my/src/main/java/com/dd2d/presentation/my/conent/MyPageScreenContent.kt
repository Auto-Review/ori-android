package com.dd2d.presentation.my.conent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.core.model.Pagination
import com.dd2d.core.presentation.list.v2.LazyListController
import com.dd2d.core.presentation.main_tab.MainPagerTab
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.domain.auth_user.user.model.User
import com.dd2d.domain.code_post.model.post.CodePostListItem
import com.dd2d.domain.code_post.model.post.CodePostListOptions
import com.dd2d.presentation.my.component.UserComponent
import com.dd2d.presentation.my.model.UserUpdateState

@Composable
internal fun MyPageScreenContent(
  user: User,
  userUpdateState: UserUpdateState,
  onUserUpdate: () -> Unit,
  codePostListContent: @Composable () -> Unit,
  tilListContent: @Composable () -> Unit,
  modifier: Modifier = Modifier
) {
  val tabs = listOf("MY CODE", "MY TIL")
  val pagerState = rememberPagerState { tabs.size }

  Column(modifier = modifier) {
    Text(
      text = "// MY PAGE",
      style = LocalHansType.current.label,
      modifier = Modifier.padding(top = 24.dp, start = 27.dp, end = 27.dp)
    )
    UserComponent(
      user = user,
      updateState = userUpdateState,
      onUpdate = onUserUpdate,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 27.dp, end = 27.dp)
    )
    MainPagerTab(
      pagerState = pagerState,
      tabs = tabs,
      pageSpacing = 24.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 32.dp)
    ) { page ->
      when (page) {
        0 -> codePostListContent()
        1 -> tilListContent()
      }
    }
  }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun MyPageScreenContentPrev() {
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
    MyPageScreenContent(
      user = User.dummy(),
      userUpdateState = UserUpdateState(),
      onUserUpdate = {},
      codePostListContent = {
        MyCodePostListContent(
          listController = LazyListController(
            option = CodePostListOptions(),
            scope = rememberCoroutineScope(),
            getList = {
              Result.success(list)
            }
          ),
          onItemClick = {},
        )
      },
      tilListContent = {},
      modifier = Modifier
    )
  }
}