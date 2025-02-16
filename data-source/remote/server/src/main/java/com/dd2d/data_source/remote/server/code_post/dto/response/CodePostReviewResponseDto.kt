package com.dd2d.data_source.remote.server.code_post.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CodePostReviewResponseDto(
    val id: Int,
    val description: String,
    val code: String,
    val createdAt: String,
)
