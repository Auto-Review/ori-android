package com.dd2d.data_source.remote.server.til.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TILAuthorResponseDto(
    val id: Int,
    val email: String,
    val nickname: String,
)
