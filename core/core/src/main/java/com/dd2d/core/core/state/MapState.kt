package com.dd2d.core.core.state

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

inline fun <reified T, reified R> Flow<DataState<T>>.mapSuccess(
    crossinline transform: suspend T.() -> R,
): Flow<DataState<R>> = this
    .map { state ->
        when(state) {
            is DataState.Loading -> DataState.Loading
            is DataState.Error -> DataState.Error(state.exception)
            is DataState.Success -> DataState.Success(transform(state.data))
        }
    }

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T, reified R> Flow<DataState<T>>.flatMapState(
    crossinline onSuccess: suspend T.() -> Flow<DataState<R>>,
): Flow<DataState<R>> = this
    .flatMapLatest { state ->
        when(state) {
            is DataState.Loading -> flowOf(DataState.Loading)
            is DataState.Error -> flowOf(DataState.Error(state.exception))
            is DataState.Success -> onSuccess(state.data)
        }
    }

inline fun <reified T> Flow<DataState<T>>.unWrap(
    default: T
): Flow<T> = this
    .map { state ->
        when(state) {
            is DataState.Loading -> default
            is DataState.Error -> default
            is DataState.Success -> state.data
        }
    }