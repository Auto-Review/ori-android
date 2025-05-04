package com.dd2d.data_source.remote.server.user.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class FCMUpdateRequestDto(
    val fcmToken: String,
)
