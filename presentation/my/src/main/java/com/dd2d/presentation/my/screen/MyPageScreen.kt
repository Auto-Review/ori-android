package com.dd2d.presentation.my.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.presentation.my.conent.MyCodePostListContent
import com.dd2d.presentation.my.conent.MyPageScreenContent
import com.dd2d.presentation.my.conent.MyTILListContent
import com.dd2d.presentation.my.model.MyPageNavigateEvent
import com.dd2d.presentation.my.view_model.MyPageViewModel

@Composable
fun MyPageScreen(
  navigationEvent: (MyPageNavigateEvent) -> Unit,
  modifier: Modifier = Modifier
) {
  val viewModel = hiltViewModel<MyPageViewModel>()

  Scaffold(
    containerColor = MaterialTheme.colorScheme.background,
    modifier = modifier
  ) { inner ->
    if (viewModel.me == null) {
      LoadingDialog()
    } else {
      MyPageScreenContent(
        user = viewModel.me!!,
        userUpdateState = viewModel.userUpdateState,
        onUserUpdate = viewModel::updateUser,
        codePostListContent = {
          MyCodePostListContent(
            listController = viewModel.codePostListController,
            onItemClick = { id -> navigationEvent(MyPageNavigateEvent.CodePost(id)) },
            modifier = Modifier.fillMaxSize(),
          )
        },
        tilListContent = {
          MyTILListContent(
            listController = viewModel.tilListController,
            onItemClick = { id -> navigationEvent(MyPageNavigateEvent.TIL(id)) },
            modifier = Modifier.fillMaxSize(),
          )
        },
        modifier = Modifier
          .consumeWindowInsets(inner)
          .fillMaxSize()
          .padding(inner)
      )
    }
  }
}