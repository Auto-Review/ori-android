package com.dd2d.data_source.remote.server.notification.dto.request


import kotlinx.serialization.Serializable

@Serializable
data class NotificationCreateRequestDto(
    val codePostId: Int,
    val reviewDay: String
)