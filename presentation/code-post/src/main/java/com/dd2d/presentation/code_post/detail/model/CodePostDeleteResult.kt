package com.dd2d.presentation.code_post.detail.model

sealed interface CodePostDeleteResult {
    data class Failure(val cause: Throwable): CodePostDeleteResult
    data object Success: CodePostDeleteResult
}