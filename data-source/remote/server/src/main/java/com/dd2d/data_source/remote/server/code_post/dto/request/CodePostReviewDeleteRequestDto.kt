package com.dd2d.data_source.remote.server.code_post.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodePostReviewDeleteRequestDto(
    val id: Int,
    val email: String
)