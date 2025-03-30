package com.dd2d.data_source.remote.server.code_post.dto.request


import kotlinx.serialization.Serializable

@Serializable
data class CodePostReviewUpdateRequestDto(
    val id: Int,
    val email: String? = null,
    val description: String? = null,
    val code: String? = null
)