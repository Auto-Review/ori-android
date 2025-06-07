package com.dd2d.presentation.search.list.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.presentation.search.list.component.SearchScreenTopBar
import com.dd2d.presentation.search.list.content.SearchScreenContent
import com.dd2d.presentation.search.list.view_model.SearchViewModel


@Composable
internal fun SearchScreen(
  onBack: () -> Unit,
  onCodePostDetailClick: (codePostId: Int) -> Unit,
  onTILDetailClick: (tilId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SearchViewModel = hiltViewModel()
) {
  Scaffold(
    topBar = { SearchScreenTopBar(searchState = viewModel.keyword, onBack = onBack) },
    modifier = modifier
  ) { inner ->
    SearchScreenContent(
      codePostListController = viewModel.codePostListController,
      tilPostListController = viewModel.tilPostListController,
      onCodePostDetailClick = onCodePostDetailClick,
      onTILDetailClick = onTILDetailClick,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }
}
