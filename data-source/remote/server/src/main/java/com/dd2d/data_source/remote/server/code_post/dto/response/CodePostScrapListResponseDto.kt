package com.dd2d.data_source.remote.server.code_post.dto.response

import com.dd2d.core.core.model.UTCString
import kotlinx.serialization.Serializable

@Serializable
data class CodePostScrapListResponseDto(
    val id: Int,
    val codePostId: Int,
    val codePostTitle: String,
    val commentCount: Int,
    val writer: String,
    val updateAt: UTCString
)
