package com.dd2d.data_source.remote.server.code_post.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CodePostUpdateRequestDto(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val level: Int? = null,
    val reviewDay: String? = null,
    val language: String? = null,
    val code: String? = null,
)


