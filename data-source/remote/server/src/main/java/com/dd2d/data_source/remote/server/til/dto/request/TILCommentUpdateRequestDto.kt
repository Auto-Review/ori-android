package com.dd2d.data_source.remote.server.til.dto.request


import kotlinx.serialization.Serializable

@Serializable
data class TILCommentUpdateRequestDto(
    val commentId: Int,
    val writerNickName: String,
    val writerEmail: String,
    val body: String,
    val isPublic: Boolean,
    val mentionNickName: String,
    val mentionEmail: String
)