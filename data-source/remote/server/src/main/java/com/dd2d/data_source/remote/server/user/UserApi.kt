package com.dd2d.data_source.remote.server.user

import com.dd2d.data_source.remote.server._common.bodyHandling
import com.dd2d.data_source.remote.server._common.isSuccessOrThrow
import com.dd2d.data_source.remote.server.user.dto.request.UserUpdateRequestDto
import com.dd2d.data_source.remote.server.user.dto.response.UserResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Named

class UserApi @Inject constructor(
    @Named("server_client")private val  client: HttpClient
) {
    suspend fun me(): UserResponseDto = client
        .get(urlString = "/v1/api/profile/info")
        .bodyHandling()

    suspend fun updateMe(body: UserUpdateRequestDto): Boolean = client
        .put(urlString = "/v1/api/profile") {
            setBody(body)
        }
        .isSuccessOrThrow()
}