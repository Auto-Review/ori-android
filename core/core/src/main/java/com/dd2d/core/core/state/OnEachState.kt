package com.dd2d.core.core.state

import com.dd2d.core.core.exception.ManagedException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <reified T> Flow<DataState<T>>.onEachState(
    crossinline onLoading: suspend () -> Unit,
    crossinline onError: suspend (exception: ManagedException) -> Unit,
    crossinline onSuccess: suspend T.() -> Unit
): Flow<DataState<T>> = this
    .onEach { state ->
        when(state) {
            is DataState.Loading -> onLoading()
            is DataState.Error -> onError(state.exception)
            is DataState.Success -> state.data.onSuccess()
        }
    }

inline fun <reified T> Flow<DataState<T>>.onStateSuccess(crossinline block: suspend T.() -> Unit): Flow<DataState<T>> = this
    .onEach { state ->
        if(state is DataState.Success) {
            state.data.block()
        }
    }

inline fun <reified T> Flow<DataState<T>>.onStateLoading(crossinline block: suspend () -> Unit): Flow<DataState<T>> = this
    .onEach { state ->
        if(state is DataState.Loading) {
            block()
        }
    }

inline fun <reified T> Flow<DataState<T>>.onStateError(crossinline block: suspend (exception: ManagedException) -> Unit): Flow<DataState<T>> = this
    .onEach { state ->
        if(state is DataState.Error) {
            block(state.exception)
        }
    }