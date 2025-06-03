package com.dd2d.core.presentation.list.v2

sealed interface LazyListControllerState {
    data object Idle: LazyListControllerState
    data class Loading(val loadType: LoadType): LazyListControllerState
    data class Error(val exception: Throwable): LazyListControllerState
}
sealed interface LoadType {
    data object Refresh: LoadType
    data object Next: LoadType
}