package com.dd2d.core.core.exception

sealed class UserException(
  message: String,
  code: Int? = null,
  cause: Throwable?
) : ManagedException(message = code?.let { "$message($code)" } ?: message, cause = cause) {
  class NetworkException(
    message: String = "",
    code: Int? = null,
    cause: Throwable? = null
  ) : UserException(message = message, code = code, cause = cause)
}