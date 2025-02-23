package com.dd2d.data_source.remote.server._common

import com.dd2d.core.core.exception.ServerException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.isSuccess

internal inline fun <R> HttpResponse.headerHandling(
    onSuccess: (Headers) -> R,
): R = with(status) {
    if(isSuccess()) onSuccess(headers)
    else throw toServerException()
}

internal suspend inline fun <reified R> HttpResponse.bodyHandling(): R = with(status) {
    if(isSuccess()) this@bodyHandling.body<ResponseDTO<R>>().data
    else throw toServerException()
}

internal fun HttpResponse.isSuccessOrThrow(): Boolean = with(status) {
    if(isSuccess()) true
    else throw toServerException()
}

private fun HttpResponse.toServerException(): ServerException {
    println("exception from server => \n\tcode : ${this.status.value}\n\tmessage : ${this.status.description}\n")
    return when(this.status.value) {
        400 -> ServerException.BadRequestException()
        401 -> ServerException.UnAuthorizationException(notFountToken = null)
        403 -> ServerException.ForbiddenException()
        404 -> ServerException.NotFoundException()
        500 -> ServerException.ExternalException()
        else -> ServerException.UnknownException()
    }
}
