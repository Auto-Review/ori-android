package com.dd2d.data_source.remote.server.til.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class TILScrapCreateRequestDto(
    val postId: Int
)
