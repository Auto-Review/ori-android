package com.dd2d.data_source.remote.server.til.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TILResponseDto(
    val id: Int,
    val title: String,
    val content: String,
    val member: TILAuthorResponseDto,
    val createDate: String,
)
