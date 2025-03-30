package com.dd2d.data_source.remote.server.code_post.dto.response


import kotlinx.serialization.Serializable

@Serializable
data class CodePostCommentListItemResponseDto(
    val id: Int,
    val parentId: Int,
    val writerId: Int,
    val writerNickName: String,
    val writerEmail: String,
    val mentionNickName: String,
    val mentionEmail: String,
    val body: String
)