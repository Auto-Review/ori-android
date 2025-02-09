package com.dd2d.core.presentation.list

import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.presentation.list.RefreshLazyListState.Error
import com.dd2d.core.presentation.list.RefreshLazyListState.Loading
import com.dd2d.core.presentation.list.RefreshLazyListState.Refreshing
import com.dd2d.core.presentation.list.RefreshLazyListState.Success

/**
 * @see Loading
 * @see Refreshing
 * @see Error
 * @see Success
 * */
sealed interface RefreshLazyListState {
    data object Loading: RefreshLazyListState
    data object Refreshing: RefreshLazyListState
    data class Error(val exception: ManagedException): RefreshLazyListState
    data object Success: RefreshLazyListState
}