package com.dd2d.data_source.remote.server.code_post.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CodePostResponseDto(
    val id: Int,
    val memberDto: CodePostAuthorResponseDto,
    val title: String,
    val level: Int,
    val reviewDay: String,
    val description: String,
    val language: String,
    val code: String,
    val dtoList: List<CodePostReviewResponseDto>,
    val createDate: String
)

