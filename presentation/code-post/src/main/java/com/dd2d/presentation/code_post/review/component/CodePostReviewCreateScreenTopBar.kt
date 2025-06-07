package com.dd2d.presentation.code_post.review.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.app_bar.CenterTitleTopBar
import com.dd2d.core.presentation.extensions.delayedClickable
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.state.LoadingIndicator
import com.dd2d.core.presentation.state.UIState
import com.dd2d.presentation.code_post.review.view_model.CodePostReviewCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CodePostReviewCreateScreenTopBar(
  viewModel: CodePostReviewCreateViewModel,
  uiState: UIState,
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
  title: String = "Review"
) {
  val keyboard = LocalSoftwareKeyboardController.current
  val createMode = viewModel.route.reviewId == null

  fun backEvent() {
    keyboard?.hide()
    onBack()
  }

  BackHandler(enabled = uiState !is UIState.Loading, onBack = ::backEvent)

  CenterTitleTopBar(
    title = title,
    onBack = ::backEvent,
    actions = {
      AnimatedVisibility(
        visible = viewModel.inputState.canSubmit,
        modifier = Modifier.padding(end = 12.dp)
      ) {
        if (uiState is UIState.Loading) {
          LoadingIndicator(width = 2.dp, modifier = Modifier.size(12.dp))
        } else {
          Main700Text(
            text = if (createMode) "완료" else "수정",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            modifier = Modifier
              .delayedClickable(
                onClick = {
                  keyboard?.hide()
                  if (createMode) viewModel.create() else viewModel.update()
                }
              )
              .padding(5.dp)
          )
        }
      }
    },
    modifier = modifier
  )
}