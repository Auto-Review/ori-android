package com.dd2d.presentation.scrap.list.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.presentation.scrap.list.content.ScrapScreenContent
import com.dd2d.presentation.scrap.list.view_model.ScrapViewModel

@Composable
internal fun ScrapScreen(
  onClose: () -> Unit,
  navigateToCodePostDetail: (codePostId: Int) -> Unit,
  navigateToTILDetail: (tilId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: ScrapViewModel = hiltViewModel()
) {
  ScrapScreenContent(
    onClose = onClose,
    codePostScrapListController = viewModel.codePostScrapListController,
    onCodePostClick = navigateToCodePostDetail,
    tilScrapListController = viewModel.tilScrapListController,
    onTILClick = navigateToTILDetail,
    modifier = modifier.fillMaxSize(),
  )
}