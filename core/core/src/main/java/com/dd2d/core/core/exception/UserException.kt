package com.dd2d.core.core.exception

sealed class UserException(
    message: String,
    code: Int? = null,
    cause: Throwable?
): ManagedException(message = code?.let { "$message($code)" }?: message, cause = cause) {
    class NetworkException(
        code: Int? = null,
        cause: Throwable? = null
    ): ClientException(message = "네트워크 연결 상태를 확인해주세요.", code = code, cause = cause)
}