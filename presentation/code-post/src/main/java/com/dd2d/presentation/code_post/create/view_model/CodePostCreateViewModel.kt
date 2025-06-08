package com.dd2d.presentation.code_post.create.view_model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.presentation._ori.ReviewTarget
import com.dd2d.core.presentation._ori.registerReviewAlarm
import com.dd2d.core.presentation.action.CommonActionResultBus
import com.dd2d.core.presentation.state.onError
import com.dd2d.core.presentation.state.onLoadingStateChanged
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.statefulResult
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.code_post.repository.CodePostRepository
import com.dd2d.presentation.code_post.create._navigation.CodePostCreateScreenRoute
import com.dd2d.presentation.code_post.create.model.CodePostFormActionCompleteResult
import com.dd2d.presentation.code_post.create.model.CodePostFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class CodePostCreateViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  @ApplicationContext private val context: Context,
  private val codePostRepository: CodePostRepository
) : ViewModel() {
  val actionBus = CommonActionResultBus()
  val route = savedStateHandle.toRoute<CodePostCreateScreenRoute>()

  val formState = CodePostFormState()
  var isLoading by mutableStateOf(false)
  val isCreateMode = route.updateCodePostId == null

  private fun initForUpdate() {
    route.updateCodePostId?.let { updateId ->
      statefulResult { codePostRepository.getCodePost(updateId) }
        .onLoadingStateChanged { isLoading = it }
        .onError(actionBus::emitFailure)
        .onSuccess(formState::initWith)
        .launchIn(viewModelScope)
    }
  }

  init {
    initForUpdate()
  }

  fun submit() {
    codePostRepository
      .withStatefulResult {
        route.updateCodePostId
          ?.let { updateId ->
            updateCodePost(update = formState.toCodePostUpdater(updateId))
          }
          ?: run {
            createCodePost(create = formState.toCodePostCreator())
          }
      }
      .onLoadingStateChanged { formState.isSubmitting = it }
      .onError(actionBus::emitFailure)
      .onSuccess { resultData ->
        val codePostId = (resultData as? Int) ?: route.updateCodePostId
        val reviewDate = formState.reviewDate

        if(reviewDate != null && codePostId != null) {
          val target = ReviewTarget.CodePost(id = codePostId, title = formState.title)
          context.registerReviewAlarm(target = target, reviewDate = reviewDate)
        }
        actionBus.newResult(CodePostFormActionCompleteResult(codePostId))
      }
      .launchIn(viewModelScope)
  }
}
