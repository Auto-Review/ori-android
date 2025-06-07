package com.dd2d.presentation.code_post.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.presentation.state.UIState
import com.dd2d.core.presentation.state.UIStateManager
import com.dd2d.domain.code_post.model.review.CodePostReviewCreator
import kotlinx.coroutines.flow.MutableStateFlow

internal class ReviewCreateState : UIStateManager {
  override val uiState = MutableStateFlow<UIState>(UIState.Idle)

  var content by mutableStateOf("")
  var description by mutableStateOf("")

  fun toCreator(codePostId: Int): CodePostReviewCreator {
    return CodePostReviewCreator(
      codePostId = codePostId,
      review = description,
      code = content,
    )
  }
}