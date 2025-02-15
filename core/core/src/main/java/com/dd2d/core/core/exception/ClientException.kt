package com.dd2d.core.core.exception

sealed class ClientException(
    message: String,
    code: Int? = null,
    cause: Throwable?
): ManagedException(message = code?.let { "$message($code)" }?: message, cause = cause) {
    class OperationFailException(
        message: String,
        code: Int? = null,
        cause: Throwable? = null
    ): ClientException(message = message, code = code, cause = cause)

    class UnknownException(
        message: String = "",
        code: Int? = null,
        cause: Throwable? = null
    ): ClientException(message = message, code = code, cause = cause)

    class UnsupportedOperationException(
        message: String = "",
        code: Int? = null,
        cause: Throwable? = null
    ): ClientException(message = message, code = code, cause = cause)
}