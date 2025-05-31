package com.dd2d.core.presentation.state

sealed interface ActionResult<out T> {
    data class Success<T>(val data: T): ActionResult<T>
    data class Failure(val cause: Throwable): ActionResult<Nothing>
}