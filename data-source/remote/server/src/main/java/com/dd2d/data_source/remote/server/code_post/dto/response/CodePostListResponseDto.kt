package com.dd2d.data_source.remote.server.code_post.dto.response

import com.dd2d.core.core.model.UTCString
import kotlinx.serialization.Serializable

@Serializable
data class CodePostListItemResponseDto(
    val id: Int,
    val writerId: Int,
    val writerEmail: String,
    val writerNickName: String,
    val title: String,
    val level: Int,
    val description: String,
    val createdDate: UTCString
)