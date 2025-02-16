package com.dd2d.data_source.remote.server._common

import com.dd2d.core.core.exception.ManagedException
import com.dd2d.core.core.exception.ServerException
import kotlinx.serialization.Serializable
import kotlin.time.TimeMark

//sealed interface Response<out T> {
//    data class Failure(val exception: ServerException): Response<Nothing>
//    data class Success<T>(val data: T): Response<T>
//}

@Serializable
internal data class ResponseDTO<T>(
    val status: String,
    val data: T,
    val message: String,
)

@Serializable
data class PagingResponseDto<I>(
    val dtoList: List<I>,
    val totalPage: Int
)