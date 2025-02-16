package com.dd2d.data_source.remote.server.code_post.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CodePostAuthorResponseDto(
    val id: Int,
    val email: String,
    val nickname: String
)