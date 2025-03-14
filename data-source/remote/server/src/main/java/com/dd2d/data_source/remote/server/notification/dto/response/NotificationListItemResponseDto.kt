package com.dd2d.data_source.remote.server.notification.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationListItemResponseDto(
    val id: Int,
    val content: String,
    val executeTime: String,
    val status: String,
    val checked: Boolean
)