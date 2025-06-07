package com.example.presentation.til.create.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.action.ActionResult
import com.dd2d.core.presentation.action.CommonActionResultBus
import com.dd2d.core.presentation.state.onError
import com.dd2d.core.presentation.state.onLoadingStateChanged
import com.dd2d.core.presentation.state.onSuccess
import com.dd2d.core.presentation.state.withStatefulResult
import com.dd2d.domain.til.repository.TILRepository
import com.example.presentation.til.create.model.TILCreateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

internal class TILCreateSuccessResult(val id: Int) : ActionResult

@HiltViewModel
internal class TILCreateViewModel @Inject constructor(
  private val tilRepository: TILRepository,
) : ViewModel() {
  val actionBus = CommonActionResultBus()

  val createState = TILCreateState()

  fun create() {
    tilRepository
      .withStatefulResult { createTIL(createState.toTILCreator()) }
      .onLoadingStateChanged { createState.isCreating = it }
      .onError(actionBus::emitFailure)
      .onSuccess { id ->
        actionBus.newResult(TILCreateSuccessResult(id))
      }
      .launchIn(viewModelScope)
  }
}