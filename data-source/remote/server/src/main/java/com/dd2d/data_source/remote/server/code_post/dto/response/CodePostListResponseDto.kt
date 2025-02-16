package com.dd2d.data_source.remote.server.code_post.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CodePostListItemResponseDto(
    val id: Int,
    val title: String,
    val level: Int,
    val description: String,
    val member: CodePostAuthorResponseDto,
    val createdDate: String
)