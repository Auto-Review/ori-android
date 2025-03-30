package com.dd2d.data_source.remote.server.til.dto.request


import kotlinx.serialization.Serializable

@Serializable
data class TILCommentCreateRequestDto(
    val postId: Int,
    val body: String,
    val isPublic: Boolean,
    val mentionNickName: String,
    val mentionEmail: String,
    val parentId: Int
)