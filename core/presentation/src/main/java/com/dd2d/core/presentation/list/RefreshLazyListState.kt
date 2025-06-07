package com.dd2d.core.presentation.list

import com.dd2d.core.core.exception.ManagedException

/**
 * @see Loading
 * @see Refreshing
 * @see Error
 * @see Success
 * */
sealed interface RefreshLazyListState {
  data object Loading : RefreshLazyListState
  data object Refreshing : RefreshLazyListState
  data class Error(val exception: ManagedException) : RefreshLazyListState
  data object Success : RefreshLazyListState
}