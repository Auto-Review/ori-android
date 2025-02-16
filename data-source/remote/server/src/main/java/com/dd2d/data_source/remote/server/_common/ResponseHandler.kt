package com.dd2d.data_source.remote.server._common

import com.dd2d.core.core.exception.ServerException
import com.dd2d.data_source.remote.server.code_post.dto.request.CodePostListOption
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

internal suspend inline fun <R> HttpResponse.headerHandling(
    onSuccess: (Headers) -> R,
): R = with(status) {
    if(isSuccess()) onSuccess(headers)
    else throw status.toServerException()
}

internal suspend inline fun <reified R> HttpResponse.bodyHandling(): R = with(status) {
    if(isSuccess()) this@bodyHandling.body<ResponseDTO<R>>().data
    else throw status.toServerException()
}

private fun HttpStatusCode.toServerException(): ServerException {
    println("exception from server => \n\tcode : ${this.value}\n\tmessage : ${this.description}\n")
    return when(this.value) {
        400 -> ServerException.BadRequestException()
        401 -> ServerException.UnAuthorizationException()
        403 -> ServerException.ForbiddenException()
        404 -> ServerException.NotFoundException()
        500 -> ServerException.ExternalException()
        else -> ServerException.UnknownException()
    }
}
