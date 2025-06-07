package com.dd2d.core.presentation.state

import com.dd2d.core.core.exception.ManagedException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed interface UIState {
  data object Idle : UIState
  data object Loading : UIState
  data class Error(val exception: ManagedException) : UIState
  data object Success : UIState
}

interface UIStateManager {
  val uiState: MutableStateFlow<UIState>

  fun stateToIdle() = uiState.update { UIState.Idle }
  fun stateToLoading() = uiState.update { UIState.Loading }
  fun stateToError(exception: ManagedException) = uiState.update { UIState.Error(exception) }
  fun stateToSuccess() = uiState.update { UIState.Success }
}

fun MutableStateFlow<UIState>.stateToIdle() = update { UIState.Idle }
fun MutableStateFlow<UIState>.stateToLoading() = update { UIState.Loading }
fun MutableStateFlow<UIState>.stateToError(exception: ManagedException) =
  update { UIState.Error(exception) }

fun MutableStateFlow<UIState>.stateToSuccess() = update { UIState.Success }