package com.dd2d.data_source.remote.server.notification

import com.dd2d.core.token_manager.TokenManager
import com.dd2d.data_source.remote.server._common.authorizationHeader
import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server.notification.dto.request.NotificationCreateRequestDto
import com.dd2d.data_source.remote.server.notification.dto.response.NotificationListItemResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class NotificationApi @Inject constructor(
    @Named("server_client") private val client: HttpClient,
    private val tokenManager: TokenManager,
) {
    suspend fun getMyNotificationList(): List<NotificationListItemResponseDto> = client
        .get(urlString = "/v1/api/notification/own") {
            authorizationHeader(tokenManager.getAccessToken())
        }
        .bodyHandling()

    suspend fun getMyUncheckedNotificationList(): List<NotificationListItemResponseDto> = client
        .get(urlString = "/v1/api/notification/own/unchecked") {
            authorizationHeader(tokenManager.getAccessToken())
        }
        .bodyHandling()

    suspend fun createNotification(creator: NotificationCreateRequestDto): Int = client
        .post(urlString = "/v1/api/notification") {
            authorizationHeader(tokenManager.getAccessToken())
            setBody(creator)
        }
        .bodyHandling()

    suspend fun toggleNotificationCheckState(notificationId: Int): Int = client
        .put(urlString = "/v1/api/notification") {
            authorizationHeader(tokenManager.getAccessToken())
            url { parameters.append(name = "id", value = "$notificationId") }
        }
        .bodyHandling()

    suspend fun deleteNotification(id: Int): Unit = client
        .delete(urlString = "/v1/api/notification/${id}") {
            authorizationHeader(tokenManager.getAccessToken())
        }
        .bodyHandling()
}