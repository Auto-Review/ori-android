package com.dd2d.data_source.remote.server.auth.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val oAuthToken: String
)
