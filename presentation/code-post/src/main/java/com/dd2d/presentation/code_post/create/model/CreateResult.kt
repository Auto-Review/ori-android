package com.dd2d.presentation.code_post.create.model

internal sealed interface CreateResult {
    data class Error(val cause: Throwable): CreateResult
    data class Success(val codePostId: Int): CreateResult
}