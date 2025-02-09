package com.dd2d.core.core.state

import com.dd2d.core.core.exception.ClientException
import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.core.exception.UserException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface DataState <out T> {
    data object Loading: DataState<Nothing>
    data class Error(val exception: ManagedException): DataState<Nothing>
    data class Success<T>(val data: T): DataState<T>
}

inline fun <reified T> Flow<T>.asDataState(flowOn: CoroutineDispatcher = Dispatchers.IO) = this
    .flowOn(flowOn)
    .map<T, DataState<T>> { DataState.Success(it) }
    .onStart { emit(DataState.Loading) }
    .catch { error ->
        val exception = when(error) {
            is ManagedException -> error

            is UnknownHostException -> UserException.NetworkException(code = 1, cause = error)
            is SocketTimeoutException -> UserException.NetworkException(code = 2, cause = error)
            is ConnectException -> UserException.NetworkException(code = 3, cause = error)
            is NoRouteToHostException -> UserException.NetworkException(code = 4, cause = error)
            is InterruptedIOException -> UserException.NetworkException(code = 5, cause = error)
            is IOException -> UserException.NetworkException(code = 6, cause = error)
            else -> ClientException.UnknownException(code = -1, throwable = error)
        }
        emit(DataState.Error(exception))
    }