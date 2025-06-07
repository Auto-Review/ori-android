package com.dd2d.presentation.code_post.create.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.core.presentation.action.CommonActionResult
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.dialog.LoadingDialog
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.message.MessageHandler
import com.dd2d.core.presentation.message.MessageHolder
import com.dd2d.core.presentation.message.rememberMessageHolder
import com.dd2d.presentation.code_post.create.content.CodePostCreateScreenContent
import com.dd2d.presentation.code_post.create.model.CodePostCreateStep
import com.dd2d.presentation.code_post.create.model.CodePostFormActionCompleteResult
import com.dd2d.presentation.code_post.create.view_model.CodePostCreateViewModel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostCreateScreen(
  onBack: () -> Unit,
  moveToCodePost: (postId: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val viewModel = hiltViewModel<CodePostCreateViewModel>()
  val messageHolder = rememberMessageHolder()

  fun backEvent() {
    messageHolder.backConfirmDialog(onBack = onBack)
  }

  LaunchedEffect(key1 = Unit) {
    viewModel.actionBus.collectResult { result ->
      val submitAction = if (viewModel.isCreateMode) "생성" else "수정"
      when (result) {
        is CommonActionResult.ActionFailure -> {
          messageHolder.defaultDialogOf {
            title = "게시물 $submitAction 실패"
            content = result.exception.message
          }
        }

        is CodePostFormActionCompleteResult -> {
          messageHolder.dialogOf {
            title = "게시물을 ${submitAction}했습니다."
            if (result.codePostId == null) {
              addAction {
                text = "확인"
                onClick = { dismissRequest ->
                  dismissRequest()
                  onBack()
                }
              }
            } else {
              content = "게시물로 이동할까요?"
              addAction {
                text = "아니오"
                onClick = { dismissRequest ->
                  dismissRequest()
                  onBack()
                }
              }
              addAction {
                text = "이동하기"
                onClick = { dismissRequest ->
                  dismissRequest()
                  onBack()
                  moveToCodePost(result.codePostId)
                }
              }
            }
          }
        }
      }
    }
  }

  MessageHandler(messageHolder = messageHolder)
  BackHandler(onBack = ::backEvent)

  Scaffold(
    topBar = {
      CenterTitleTopBar(
        title = if (viewModel.isCreateMode) "생성" else "수정",
        onBack = ::backEvent,
        actions = {
          CodePostCreateStepButton(
            currentStep = viewModel.formState.step,
            onNextStep = viewModel.formState::nextStep,
            onPrevStep = viewModel.formState::prevStep,
            canComplete = viewModel.formState.canSubmit,
            onComplete = viewModel::submit,
            isLoading = viewModel.formState.isSubmitting
          )
        }
      )
    },
    modifier = modifier
  ) { inner ->
    if (viewModel.isLoading) {
      LoadingDialog()
    } else {
      CodePostCreateScreenContent(
        createState = viewModel.formState,
        modifier = Modifier
          .consumeWindowInsets(inner)
          .fillMaxSize()
          .padding(inner)
      )
    }
  }
}

@Composable
private fun CodePostCreateStepButton(
  currentStep: CodePostCreateStep,
  onNextStep: () -> Unit,
  onPrevStep: () -> Unit,
  onComplete: () -> Unit,
  isLoading: Boolean,
  canComplete: Boolean,
  modifier: Modifier = Modifier
) {
  val keyboard = LocalSoftwareKeyboardController.current
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
  ) {
    if (isLoading) {
      CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 2.dp,
        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
        modifier = Modifier.size(24.dp)
      )
    } else {
      if (currentStep.ordinal > 0) {
        Main700Text(
          text = "이전",
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 12.sp,
          lineHeight = 24.sp,
          modifier = Modifier
            .clickable(onClick = onPrevStep)
            .padding(10.dp)
        )
      }
      if (currentStep.ordinal in 0..<CodePostCreateStep.entries.lastIndex) {
        Main700Text(
          text = "다음",
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 12.sp,
          lineHeight = 24.sp,
          modifier = Modifier
            .clickable(onClick = onNextStep)
            .padding(10.dp)
        )
      }
      if (currentStep.ordinal == CodePostCreateStep.entries.lastIndex) {
        AnimatedVisibility(
          visible = canComplete
        ) {
          Main700Text(
            text = "완료",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            modifier = Modifier
              .clickable(enabled = canComplete) {
                keyboard?.hide()
                onComplete()
              }
              .padding(10.dp)
          )
        }
      }
    }
  }
}
