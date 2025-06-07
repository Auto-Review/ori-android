package com.example.presentation.til.create.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.core.presentation.action.CommonActionResult
import com.dd2d.core.presentation.message.MessageHolder
import com.dd2d.core.presentation.message.rememberMessageHolder
import com.dd2d.core.presentation.scaffold.MessageHandlerScaffold
import com.example.presentation.til.create.component.TILScreenTopBar
import com.example.presentation.til.create.content.TILCreateScreenContent
import com.example.presentation.til.create.view_model.TILCreateSuccessResult
import com.example.presentation.til.create.view_model.TILCreateViewModel

private fun MessageHolder.backConfirmDialog(onBack: () -> Unit) {
  dialogOf {
    title = "뒤로가기"
    content = "작성한 내용은 저장되지 않습니다.\n뒤로 가시겠습니까?"
    addAction { text = "취소" }
    addAction {
      text = "뒤로가기"
      onClick = { dismissRequest ->
        dismissRequest()
        onBack()
      }
    }
  }
}

@Composable
fun TILCreateScreen(
  onBack: () -> Unit,
  navigateToTILDetail: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val viewModel = hiltViewModel<TILCreateViewModel>()
  val messageHolder = rememberMessageHolder()

  fun backEvent() {
    messageHolder.backConfirmDialog(onBack = onBack)
  }

  LaunchedEffect(key1 = Unit) {
    viewModel.actionBus.collectResult { result ->
      when (result) {
        is CommonActionResult.ActionFailure -> {
          messageHolder.defaultDialogOf {
            title = result.exception.message
          }
        }

        is TILCreateSuccessResult -> {
          messageHolder.dialogOf {
            title = "게시물이 생성되었습니다."
            content = "게시물로 이동할까요?"
            addAction {
              text = "아니오"
              onClick = { dismissRequest ->
                dismissRequest()
                onBack()
              }
            }
            addAction {
              text = "이동"
              onClick = { dismissRequest ->
                dismissRequest()
                onBack()
                navigateToTILDetail(result.id)
              }
            }
          }
        }
      }
    }
  }

  BackHandler(onBack = ::backEvent)
  MessageHandlerScaffold(
    messageHolder = messageHolder,
    topBar = {
      TILScreenTopBar(
        onBack = ::backEvent,
        onCreate = viewModel::create,
        isCreating = viewModel.createState.isCreating
      )
    },
    modifier = modifier
  ) { inner ->
    TILCreateScreenContent(
      createState = viewModel.createState,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }
}