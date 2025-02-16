package com.dd2d.data_source.remote.server.auth.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken: String,
)
