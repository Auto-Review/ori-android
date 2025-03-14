package com.dd2d.data_source.remote.server.code_post.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CodePostCommentUpdateRequestDto(
    val commentId: Int,
    val writerNickName: String? = null,
    val writerEmail: String? = null,
    val body: String? = null,
    val isPublic: Boolean? = null,
    val mentionNickName: String? = null,
    val mentionEmail: String? = null
)