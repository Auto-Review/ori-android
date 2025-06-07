package com.example.presentation.til.list.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.til.list.content.TILListScreenContent
import com.example.presentation.til.list.view_model.TILListViewModel

@Composable
fun TILListScreen(
  onTILClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val viewModel = hiltViewModel<TILListViewModel>()

  Scaffold { inner ->
    TILListScreenContent(
      listController = viewModel.tilListController,
      onItemClick = onTILClick,
      modifier = modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }
}