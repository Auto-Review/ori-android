package com.dd2d.core.core.exception

sealed class ServerException(
    message: String,
    code: Int? = null,
    cause: Throwable?
): ManagedException(message = code?.let { "$message($code)" }?: message, cause = cause) {
    class BadRequestException(
        message: String = "",
        code: Int? = 400,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)

    class UnAuthorizationException(
        message: String = "",
        code: Int? = 401,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)

    class ForbiddenException(
        message: String = "",
        code: Int? = 403,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)

    class NotFoundException(
        message: String = "",
        code: Int? = 404,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)

    class ExternalException(
        message: String = "",
        code: Int? = 500,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)

    class UnknownException(
        message: String = "",
        code: Int? = null,
        cause: Throwable? = null
    ): ServerException(message = message, code = code, cause = cause)
}