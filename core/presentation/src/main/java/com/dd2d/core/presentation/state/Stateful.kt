package com.dd2d.core.presentation.state

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

sealed interface Stateful<out T> {
  data object Loading : Stateful<Nothing>
  data class Error(val cause: Throwable) : Stateful<Nothing>
  data class Success<T>(val data: T) : Stateful<T>
}


fun <T> statefulResult(block: suspend () -> Result<T>): Flow<Stateful<T>> {
  return flow<Stateful<T>> { emit(Stateful.Success(block().getOrThrow())) }
    .onStart { emit(Stateful.Loading) }
    .catch { emit(Stateful.Error(it)) }
}

fun <T, R> T.withStatefulResult(block: suspend T.() -> Result<R>): Flow<Stateful<R>> {
  return flow<Stateful<R>> { emit(Stateful.Success(this@withStatefulResult.block().getOrThrow())) }
    .onStart { emit(Stateful.Loading) }
    .catch { emit(Stateful.Error(it)) }
}

fun <T> Result<T>.asStatefulResult(): Flow<Stateful<T>> {
  return flow<Stateful<T>> { emit(Stateful.Success(this@asStatefulResult.getOrThrow())) }
    .onStart { emit(Stateful.Loading) }
    .catch { emit(Stateful.Error(it)) }
}


fun <T> Flow<Stateful<T>>.onLoading(block: suspend () -> Unit): Flow<Stateful<T>> {
  return onEach { state ->
    if (state is Stateful.Loading) {
      block()
    }
  }
}

fun <T> Flow<Stateful<T>>.onError(block: suspend (cause: Throwable) -> Unit): Flow<Stateful<T>> {
  return onEach { state ->
    if (state is Stateful.Error) {
      block(state.cause)
    }
  }
}

fun <T> Flow<Stateful<T>>.onSuccess(block: suspend (data: T) -> Unit): Flow<Stateful<T>> {
  return onEach { state ->
    if (state is Stateful.Success) {
      block(state.data)
    }
  }
}


fun <T> Flow<Stateful<T>>.onEachState(
  onLoading: suspend () -> Unit,
  onError: suspend (cause: Throwable) -> Unit,
  onSuccess: suspend (data: T) -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    when (state) {
      is Stateful.Loading -> onLoading()
      is Stateful.Error -> onError(state.cause)
      is Stateful.Success -> onSuccess(state.data)
    }
  }
}

fun <T> Flow<Stateful<T>>.onLoadingStateChanged(
  onChange: (isLoading: Boolean) -> Unit
): Flow<Stateful<T>> {
  return onEach { state ->
    onChange(state is Stateful.Loading)
  }
}


fun <T, R> Flow<Stateful<T>>.mapSuccess(onSuccess: (T) -> R): Flow<Stateful<R>> {
  return map { state ->
    when (state) {
      is Stateful.Loading -> Stateful.Loading
      is Stateful.Error -> Stateful.Error(state.cause)
      is Stateful.Success -> Stateful.Success(onSuccess(state.data))
    }
  }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, R> Flow<Stateful<T>>.flatMapSuccess(onSuccess: (T) -> Flow<Stateful<R>>): Flow<Stateful<R>> {
  return flatMapLatest {
    when (it) {
      is Stateful.Loading -> flowOf(Stateful.Loading)
      is Stateful.Error -> flowOf(Stateful.Error(it.cause))
      is Stateful.Success -> onSuccess(it.data)
    }
  }
}