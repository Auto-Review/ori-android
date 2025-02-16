package com.dd2d.data_source.remote.server.code_post.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CodePostCreateRequestDto(
    val title: String,
    val level: Int,
    val reviewDay: String,
    val description: String,
    val language: String,
    val code: String,
)